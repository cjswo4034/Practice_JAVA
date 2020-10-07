package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class RPG_Extreme_17081 {
    static final int[][] DIR = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int n, m, k, l, startR, startC, trapDamage = 5;
    static int[] cmds;
    static char[][] map;
    static MainCharacter mainCharacter;
    static Map<Point, Object> objects = new HashMap<>();
    static Map<String, Accessory> accessoryMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        init();
        int turn = 0;
        for (; turn < cmds.length; turn++) {
            if(mainCharacter.action(cmds[turn])) {
                turn++;
                break;
            }
        }

        if (!mainCharacter.status.isDied()) map[mainCharacter.r][mainCharacter.c] = '@';
        System.out.println(getMap() + "Passed Turns : " + turn + "\n" + mainCharacter);
    }

    static String getMap() {
        StringBuilder sb = new StringBuilder();
        for (char[] r: map) {
            for (char ch: r) sb.append(ch);
            sb.append("\n");
        }
        return sb.toString();
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        initMap(br);
        initEntity(br);
    }

    static void initMap(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        startR = startC = 0;
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '&' || map[i][j] == 'M') k++;
                else if (map[i][j] == 'B') l++;
                else if (map[i][j] == '@') {
                    startR = i;
                    startC = j;
                    map[i][j] = '.';
                }
            }
        }
    }

    static void initEntity(BufferedReader br) throws Exception {
        mainCharacter = new MainCharacter(startR, startC);

        Map<java.lang.Character, Integer> direction = new HashMap<>();
        StringTokenizer st;
        String inputCmd;
        Point point;

        direction.put('L', 0);
        direction.put('R', 1);
        direction.put('U', 2);
        direction.put('D', 3);

        inputCmd = br.readLine();
        cmds = new int[inputCmd.length()];
        for (int i = 0; i < inputCmd.length(); i++) cmds[i] = direction.get(inputCmd.charAt(i));

        for (int i = 0, r, c; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            point = new Point(r, c);
            objects.put(point, new Monster(st));
            if (map[r][c] == 'M') ((Monster) objects.get(point)).isBoss = true;
        }

        for (int i = 0, r, c; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            point = new Point(r, c);
            objects.put(point, new Item(st));
        }

        accessoryMap.put("HR", new HR());
        accessoryMap.put("RE", new RE());
        accessoryMap.put("CO", new CO());
        accessoryMap.put("EX", new EX());
        accessoryMap.put("DX", new DX());
        accessoryMap.put("HU", new HU());
        accessoryMap.put("CU", new CU());
    }

    static class MainCharacter {
        String message = "Press any key to continue.";
        Status status;
        List<Accessory> accessories;
        Equipment weapon, armor;

        boolean superArmor = false, hasDx = false;
        int removeRe = -1;
        int r, c;

        MainCharacter(int r, int c) {
            this.r = r;
            this.c = c;
            this.status = new Status(20, 2, 2, 5);
            this.accessories = new ArrayList<>();
        }

        void levelUp() {
            if (status.curExp < status.maxExp) return;
            status.lv++;
            status.curExp = 0;
            status.maxExp = status.lv * 5;
            status.nA = status.nW = status.lv * 2;
            status.curHp = status.remHp = 15 + status.lv * 5;
        }

        boolean action(int direction) {
            char nextEntity = move(direction);
            if (nextEntity == '.') return false;
            Point point = new Point(r, c);

            Object object = objects.get(point);
            Monster monster = null;
            if (object instanceof Monster) monster = (Monster) object;

            actBeforeAction(monster);

            switch (nextEntity) {
                case '^': status.damaged(trapDamage); break;
                case '&':
                case 'M': fight(monster); break;
                case 'B': acquireItem((Item) objects.get(point)); break;
            }

            if (object != null) {
                if (object instanceof Monster && ((Monster) object).status.isDied()) objects.remove(point);
                else if (object instanceof Item) objects.remove(point);
            }

            if (!status.isDied() && map[r][c] != '^') map[r][c] = '.';
            return actAfterAction(monster);
        }

        void fight(Monster monster) {
            Status user = status;
            Status mob = monster.status;

            // 데미지 보너스
            user.attack(mob);
            user.bonusDamage = 1;
            if (mob.isDied()) return;

            // 슈퍼아머
            if (!(superArmor && monster.isBoss)) mob.attack(user);

            while (!user.isDied()) {
                user.attack(mob);
                if (mob.isDied()) break;
                mob.attack(user);
            }
        }

        char move(int direction) {
            int nr = r + DIR[direction][0];
            int nc = c + DIR[direction][1];

            if (nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == '#') {
                nr = r;
                nc = c;
            }

            r = nr;
            c = nc;

            return map[r][c];
        }

        void actBeforeAction(Monster monster) {
            for (Accessory accessory: accessories)
                accessory.effectBeforeAction(this, monster);
        }

        boolean actAfterAction(Monster monster) {
            if (monster != null && monster.status.isDied()) {
                status.curExp += monster.getExp();
                levelUp();
            }

            for (Accessory accessory: accessories) accessory.effectAfterAction(this, monster);
            if (removeRe != -1) {
                accessories.remove(removeRe);
                removeRe = -1;
            }

            if (status.isDied()) {
                message = "YOU HAVE BEEN KILLED BY ";
                if (monster != null) message += monster.name + "..";
                else message += "SPIKE TRAP..";
                return true;
            } else if (monster != null && monster.isBoss() && monster.status.isDied()) {
                message = "YOU WIN!";
                return true;
            }

            return false;
        }

        void acquireItem(Item item) {
            Object obj = item.getItem();
            if (obj instanceof Equipment) equip((Equipment) obj);
            else equip((Accessory) obj);
        }

        void equip(Equipment equipment) {
            if (equipment.type == 'A') {
                armor = equipment;
                status.a = armor.value;
            } else {
                weapon = equipment;
                status.w = weapon.value;
            }
        }

        void equip(Accessory accessory) {
            if (accessories.size() >= 4 || accessories.contains(accessory)) return;
            accessories.add(accessory);
            switch (accessory.getType()) {
                case "EX": ((EX) accessory).effect(); break;
                case "HU": superArmor = true; break;
                case "DX": hasDx = true; trapDamage = 1; break;
            }
        }

        public void removeItem(RE re) {
            removeRe = accessories.indexOf(re);
        }

        @Override
        public String toString() {
            return status + "\n" + message;
        }
    }

    static class Monster {
        boolean isBoss;
        String name;
        Status status = new Status();

        Monster (StringTokenizer st) {
            name = st.nextToken();
            status.nW = Integer.parseInt(st.nextToken());
            status.nA = Integer.parseInt(st.nextToken());
            status.remHp = status.curHp = Integer.parseInt(st.nextToken());
            status.maxExp = Integer.parseInt(st.nextToken());
        }

        int getExp() {
            return status.maxExp;
        }

        boolean isBoss() {
            return isBoss;
        }

        @Override
        public String toString() {
            return "Monster{" +
                    "isBoss=" + isBoss +
                    ", name='" + name + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    static class Item {
        char type;
        String status;

        Item(StringTokenizer st) {
            type = st.nextToken().charAt(0);
            status = st.nextToken();
        }

        Object getItem() {
            if (type == 'O') return toAccessory();
            else return toEquipment();
        }

        Equipment toEquipment() {
            int value = Integer.parseInt(status);
            return new Equipment(value, type);
        }

        Accessory toAccessory() {
            return accessoryMap.get(status);
        }
    }

    static class Equipment {
        int value;
        char type;
        Equipment (int value, char type) {
            this.value = value;
            this.type = type;
        }
    }

    abstract static class Accessory {
        abstract void effectAfterAction(MainCharacter character, Monster monster);
        abstract void effectBeforeAction(MainCharacter character, Monster monster);
        abstract String getType();

        @Override
        public boolean equals(Object obj) {
            return getType().equals(((Accessory)obj).getType());
        }

        @Override
        public String toString() {
            return getType();
        }
    }

    static class HR extends Accessory {
        @Override
        void effectAfterAction(MainCharacter character, Monster monster) {
            if (!character.status.isDied() && monster != null && monster.status.isDied()) character.status.recover(3);
        }

        @Override
        void effectBeforeAction(MainCharacter character, Monster monster) {}

        @Override
        String getType() {
            return "HR";
        }
    }

    static class RE extends Accessory {
        @Override
        void effectAfterAction(MainCharacter character, Monster monster) {
            if (!character.status.isDied()) return;
            if (monster != null) monster.status.recover(monster.status.curHp);
            character.status.recover(character.status.curHp);
            character.r = startR;
            character.c = startC;
            character.removeItem(this);
        }

        @Override
        void effectBeforeAction(MainCharacter character, Monster monster) {}

        @Override
        String getType() {
            return "RE";
        }
    }

    static class CO extends Accessory {
        @Override
        void effectAfterAction(MainCharacter character, Monster monster) {
            character.status.bonusDamage = 1;
        }

        @Override
        void effectBeforeAction(MainCharacter character, Monster monster) {
            if (character.hasDx) character.status.bonusDamage = 3;
            else character.status.bonusDamage = 2;
        }

        @Override
        String getType() {
            return "CO";
        }
    }

    static class EX extends Accessory {
        @Override
        void effectAfterAction(MainCharacter character, Monster monster) {}

        @Override
        void effectBeforeAction(MainCharacter character, Monster monster) {}

        void effect() {
            for (Map.Entry<Point, Object> entry: objects.entrySet()) {
                if (entry.getValue() instanceof Monster) {
                    ((Monster) entry.getValue()).status.maxExp = (int)((double)((Monster) entry.getValue()).status.maxExp * 1.2);
                }
            }
        }

        @Override
        String getType() {
            return "EX";
        }
    }

    static class DX extends Accessory {
        @Override
        void effectAfterAction(MainCharacter character, Monster monster) {}

        @Override
        void effectBeforeAction(MainCharacter character, Monster monster) {}

        @Override
        String getType() {
            return "DX";
        }
    }

    static class HU extends Accessory {
        @Override
        void effectAfterAction(MainCharacter character, Monster monster) {}

        @Override
        void effectBeforeAction(MainCharacter character, Monster monster) {
            if (monster != null && monster.isBoss())
                character.status.recover(character.status.curHp);
        }

        @Override
        String getType() {
            return "HU";
        }
    }

    static class CU extends Accessory {
        @Override
        void effectAfterAction(MainCharacter character, Monster monster) {}

        @Override
        void effectBeforeAction(MainCharacter character, Monster monster) {}

        @Override
        String getType() {
            return "CU";
        }
    }

    static class Status {
        int lv, remHp, curHp, nW, w, nA, a, curExp, maxExp;
        int bonusDamage = 1;
        public Status() {}

        public Status(int curHp, int nW, int nA, int maxExp) {
            this.lv = 1;
            this.curHp = this.remHp = curHp;
            this.nW = nW;
            this.nA = nA;
            this.maxExp = maxExp;
        }

        public void recover(int value) {
            remHp = Math.min(remHp + value, curHp);
        }

        public int getDamage() {
            return nW + w;
        }

        public int getArmor() {
            return nA + a;
        }

        public void damaged(int damage) {
            remHp = Math.max(0, remHp - damage);
        }

        public void attack(Status enemy) {
            enemy.damaged(Math.max(1, getDamage() * bonusDamage - enemy.getArmor()));
        }

        public boolean isDied() {
            return remHp <= 0;
        }

        @Override
        public String toString() {
            return "LV : " + lv + "\nHP : " + remHp + "/" + curHp + "\nATT : " + nW + "+" + w + "\nDEF : " + nA + "+" + a + "\nEXP : " + curExp + "/" + maxExp;
        }
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            Point point = (Point) o;
            if (r != point.r) return false;
            return c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r * 31, c);
        }
    }
}