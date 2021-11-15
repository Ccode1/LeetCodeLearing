package com.yhs.twentydays;

public class maxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int clo = grid[0].length;
        if(row == 0 || clo == 0)
            return 0;
        int res = 0;
        for(int i =0 ;i < row;i++){
            for(int j = 0;j <clo;j++){
                if(grid[i][j] == 1){
                    res = Math.max(dfs(grid,i,j,row,clo),res);
                }
            }
        }
        return res;
    }
    public int dfs(int[][]nums,int i,int j,int row,int clo){
        if(i<0 || j<0 ||i >= row||j >= clo ||nums[i][j] == 0){
            return 0;
        }
        nums[i][j] = 0;
        return 1+ dfs(nums,i-1,j,row,clo)+dfs(nums,i+1,j,row,clo)+dfs(nums,i,j-1,row,clo)+dfs(nums,i,j+1,row,clo);
    }

}
