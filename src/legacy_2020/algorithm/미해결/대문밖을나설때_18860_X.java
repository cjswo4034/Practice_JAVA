package algorithm.미해결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// TODO 못품
public class 대문밖을나설때_18860_X {
    static int n, minIdx, depth;
    static long minCnt, leafFrom, leafTo;
    static OilTank[] oilTanks;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        depth = getDepth(n);
        oilTanks = new OilTank[n + 1];
        leafFrom = (long)Math.pow(2, depth - 1);
        leafTo = (long)Math.pow(2, depth) - 1;
        minCnt = 987654321;

        st = new StringTokenizer(br.readLine());
        oilTanks[0] = new OilTank(0, 0);
        for (int i = 1; i <= n; i++) oilTanks[i] = new OilTank(i, Long.parseLong(st.nextToken()));

        getMin(1);
        System.out.println(simulation(minIdx));
    }

    // leaf 까지 도달하는데 가장 작은 leaf index
    static void getMin(int idx) {
        if (oilTanks[idx].isLeaf) {
            if (minCnt > oilTanks[idx].tot) {
                minCnt = oilTanks[idx].tot;
                minIdx = idx;
            }
            return;
        }

        long l = oilTanks[idx * 2].tot;
        long r = oilTanks[idx * 2 + 1].tot;
        if (l <= r) getMin(idx * 2);
        else getMin(idx * 2 + 1);
    }

    static int getDepth(long n) {
        int res = 0;
        while (Math.pow(2, res) - 1 != n) res++;
        return res;
    }

    // 원유를 한번에 채우기. 최소(remain / production)만큼
    // t=1에 leaf[idx]의 펌프가 작동됐을 때 석유가 다 차는 시간
    static long simulation(int idx) {
        Queue<OilTank> q = new LinkedList<>();
        OilTank tank, next;
        long res = 0;

        q.add(oilTanks[idx]);
        while (!oilTanks[1].oil.isFull()) {
            int size = q.size(), min = 987654321;

            for (OilTank oilTank: q) min = Math.min(min, (int)(oilTank.oil.getRemain() / oilTank.production));
            min = Math.max(1, min);
            System.out.println(min);
            List<OilTank> terminatedTank = new ArrayList<>();

            while (size-- > 0) {
                tank = q.poll();

                // 원유 생산
                tank.produceOil(min);

                // 원유 분배 (분배했을 때 터미널 노드라면 q에 추가)
                OilTank.Oil oil = new OilTank.Oil(tank.oil.amount, tank.oil.maxAmount);
                tank.oil.amount = 0;
                q.addAll(tank.distribute(oil, tank.idx));

                // 원유 생산이 종료되면 생산량을 상위 탱크에 넘긴다.
                if (!tank.isTerminated) q.add(tank);
                else if(tank.idx != 1) terminatedTank.add(tank);
            }

            for(OilTank oilTank: terminatedTank) {
                next = oilTanks[oilTank.idx / 2];
                while (next.idx != 1 && next.isTerminated) next = oilTanks[next.idx / 2];
                next.production += oilTank.production;
                if (!q.contains(next)) q.add(next);
            }

            res += min;
        }

        return res - 1;
    }

    static class OilTank {
        int idx, left, right;
        long production, tot;
        Oil oil;
        boolean isTerminated, isLeaf;

        public OilTank(int idx, long maximum) {
            this.idx = idx;
            this.tot = maximum;
            this.oil = new Oil(maximum);
            if (idx * 2 > leafTo) {
                isLeaf = true;
                production = 1;
            } else {
                left = idx * 2;
                right = left + 1;
            }

            while (idx > 1) {
                oilTanks[idx / 2].tot += tot;
                idx /= 2;
            }
        }

        // 1. 오일을 생산한다.
        public void produceOil(int mul) {
            oil.amount = oil.amount + production * mul;
            if (oil.isFull() && idx != 1) isTerminated = true;
        }

        // 2. 탱크의 원유를 조정한다.
        public List<OilTank> distribute(Oil oil, long from) {
            List<OilTank> res = new ArrayList<>();
            if (oil.amount == 0) return res;

            // 1. 오일이 남았으면 왼쪽을 채운다.
            // 2. 오일이 남았으면 오른쪽을 채운다.
            if (!isLeaf) {
                if (this.oil != oil) {
                    oil.amount += this.oil.amount;
                    this.oil.amount = 0;
                }

                if (!oilTanks[left].oil.isFull() && !oilTanks[right].oil.isFull()) {
                    Oil l = new Oil(oil.amount / 2, oil.maxAmount);
                    Oil r = new Oil(oil.amount / 2, oil.maxAmount);
                    if (oil.amount % 2 == 1) {
                        if (oilTanks[left].tot < oilTanks[right].tot) l.amount++;
                        else r.amount++;
                    }
                    if (!oilTanks[left].oil.isFull()) res.addAll(oilTanks[left].distribute(l, from));
                    if (l.amount > 0) {
                        r.amount += l.amount;
                        l.amount = 0;
                    }
                    if (!oilTanks[right].oil.isFull()) res.addAll(oilTanks[right].distribute(r, from));
                    oil.amount = l.amount + r.amount;
                } else if (!oilTanks[right].oil.isFull()) res.addAll(oilTanks[right].distribute(oil, from));
                else if (!oilTanks[left].oil.isFull()) res.addAll(oilTanks[left].distribute(oil, from));
            }

            if (oil.amount == 0) return res;

            // 3. 오일이 남았으면 자신을 채운다.
            oil.fill(this);
            if (from != idx && isLeaf) res.add(this);

            // 4. 오일이 남았으면 상위 탱크를 채운다.
            if (oil.amount > 0 && idx != 1) res.addAll(oilTanks[idx / 2].distribute(oil, from));

            return res;
        }

        static class Oil {
            long amount, maxAmount;

            public Oil(long amount) {
                this.maxAmount = amount;
            }

            public Oil(long amount, long maxAmount) {
                this.amount = amount;
                this.maxAmount = maxAmount;
            }

            public boolean isFull() {
                return amount >= maxAmount;
            }

            public long getRemain() {
                return maxAmount - amount;
            }

            public void fill(OilTank tank) {
                if (tank.oil.getRemain() >= amount) {
                    tank.oil.amount += amount;
                    amount = 0;
                } else {
                    amount -= tank.oil.getRemain();
                    tank.oil.amount = tank.oil.maxAmount;
                }
            }

            @Override
            public String toString() {
                return "Oil{" + amount +
                        " / " + maxAmount +
                        '}';
            }
        }

        @Override
        public boolean equals(Object o) {
            OilTank tank = (OilTank) o;
            return idx == tank.idx;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(idx);
        }

        @Override
        public String toString() {
            return "OilTank{" +
                    "idx=" + idx +
                    ", tot=" + tot +
                    ", prod=" + production +
                    ", oil=" + oil +
                    '}';
        }
    }
}
