class DSU {
    int[] parent;
    int[] rank;

    DSU(int n){
        parent = new int[n];
        rank = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int find(int x){
        if(parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb) return;

        if(rank[pa] < rank[pb]){
            parent[pa] = pb;
        } else if(rank[pa] > rank[pb]){
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }
    }
}
