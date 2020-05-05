#include<bits/stdc++.h>
using namespace std;
#define cin fin
ifstream fin;
vector<vector<pair<int,int> > > p;
vector<int> dp;
vector<vector<int> > dist;
vector<int> aboard;
int n,m,k,s,t;
int arez;
vector<int> djikstra(int s){
	priority_queue<pair<int,int> > pq;
	pq.push(make_pair(0,s));
	vector<int> dist(n,-1);
	dist[s]=0;
	while(pq.size()){
		int pr=-pq.top().first;
		int ind=pq.top().second;
		pq.pop();
		for(pair<int,int> ng: p[ind]){
			int ni=ng.first;
			int nd=pr+ng.second;
			if(dist[ni]!=-1&&dist[ni]<nd)continue;
			dist[ni]=nd;
			pq.push(make_pair(-nd,ni));
		}
	}
	return dist;
}
int calc(vector<int> g){
	int rez=-1;
	do{
		int crez=aboard[g[0]];
		for(int i = 1 ; i < g.size() ; i ++)crez+=dist[g[i-1]][g[i]];
		if(rez==-1||rez>crez)rez=crez;
	}while(next_permutation(g.begin(),g.end()));
	return rez;
}
void solve(){
	cin>>n>>m;
	p.clear();
	p.resize(n);
	for(int i = 0,a,b,c,d ; i < m ; i ++){
		cin>>d>>a>>b>>c;a--;b--;
		p[a].push_back(make_pair(b,c));
		if(d==2)
			p[b].push_back(make_pair(a,c));
	}
	cin>>t;
	cin>>s;s--;
	cin>>k;
	vector<int> pos(k);
	for(int i = 0 ; i < k ; i ++){
		cin>>pos[i];pos[i]--;
	}
	aboard.clear();
	aboard.resize(k);
	dist.clear();
	dist.resize(k,vector<int>(k));
	vector<int> sp=djikstra(s);
	for(int i = 0 ; i < k ; i ++){
		aboard[i]=sp[pos[i]];
		vector<int> g=djikstra(pos[i]);
		for(int j = 0 ; j < k ; j ++)
			dist[i][j]=g[pos[j]];
	}
	dp.clear();
	dp.resize(1<<k);
	dp[0]=0;
	for(int i = 1 ; i < (1<<k) ; i ++){
		vector<int> bits;
		for(int j = i,l=0 ; j ; j/=2,l++)if(j%2)bits.push_back(l);
		dp[i]=dp[i-(1<<bits.back())]+t+aboard[bits.back()];
		vector<int> g(2);
		g.back()=bits.back();
		for(int c1=0;c1<bits.size()-1;c1++){
			g[0]=bits[c1];
			int sm=calc(g);
			if(dp[i-(1<<bits.back())-(1<<g[0])]+t+sm<dp[i]){
				dp[i]=dp[i-(1<<bits.back())-(1<<g[0])]+t+sm;
			}
		}
		g.resize(3);
		g.back()=bits.back();
		for(int c1=0;c1<bits.size()-1;c1++){
			g[0]=bits[c1];
			for(int c2=c1+1;c2<bits.size()-1;c2++){
				g[1]=bits[c2];
				int sm=calc(g);
				if(dp[i-(1<<bits.back())-(1<<g[0])-(1<<g[1])]+t+sm<dp[i]){
					dp[i]=dp[i-(1<<bits.back())-(1<<g[0])-(1<<g[1])]+t+sm;
				}
			}
		}
		g.resize(4);
		g.back()=bits.back();
		for(int c1=0;c1<bits.size()-1;c1++){
			g[0]=bits[c1];
			for(int c2=c1+1;c2<bits.size()-1;c2++){
				g[1]=bits[c2];
				for(int c3=c2+1;c3<bits.size()-1;c3++){
					g[2]=bits[c3];
					int sm=calc(g);
					if(dp[i-(1<<bits.back())-(1<<g[0])-(1<<g[1])-(1<<g[2])]+t+sm<dp[i]){
						dp[i]=dp[i-(1<<bits.back())-(1<<g[0])-(1<<g[1])-(1<<g[2])]+t+sm;
					}
				}
			}
		}
	}
	cout<<(arez=dp[(1<<k)-1])<<" ";
}
int main(){
	///for working with cin
//	solve();

	///for working with fin, if you uncomment the following lines, make sure to also uncomment the lines 3 and 4, and comment the previous line

	 for(int j = 1 ; j < 36 ; j ++){
	 	if(j<10)	///set the destination path accordingly
	 		fin.open("E/00"+to_string(j)+".in");
	 	else
	 		fin.open("E/0" +to_string(j)+".in");
	 	solve();
	 	fin.close();
	 	ifstream cmp;
	 	if(j<10)
	 		cmp.open("E/00"+to_string(j)+".out");
	 	else
	 		cmp.open("E/0" +to_string(j)+".out");
	 	int tc;
	 	cmp>>tc;
	 	cout<<tc<<endl;
	 	if(arez!=tc)cout<<"WROOONG "<<j<<endl;
	 	else 		cout<<"OOOKKKK "<<j<<endl;
	 }
}