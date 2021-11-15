package com.yhs.twentydays;

public class floodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int target = image[sr][sc];
        newColor += target;
        int row = image.length;
        int clos = image[0].length;
        boolean[][] flag = new boolean[row][clos];
        image[sr][sc] = newColor;
        dfs(image,flag,sr,sc,target,newColor,row,clos);
        return image;
    }

    private boolean dfs(int[][] image, boolean[][] flag, int i, int j, int target, int newColor, int row, int clos) {
        if(i < 0 || j < 0 || i >= row || j >= clos || image[i][j] != target || flag[i][j]){
            return false;
        }
        flag[i][j] = true;
        if(dfs(image,flag,i-1,j,target,newColor,row,clos)||
                dfs(image,flag,i+1,j,target,newColor,row,clos)||
                dfs(image,flag,i,j-1,target,newColor,row,clos)||
                dfs(image,flag,i,j+1,target,newColor,row,clos)
        ){
            image[i][j] = newColor;
        }else{
            return false;
        }
        return true;
    }
}
