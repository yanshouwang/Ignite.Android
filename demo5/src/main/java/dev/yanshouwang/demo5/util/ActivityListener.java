package dev.yanshouwang.demo5.util;

public interface ActivityListener {
    /**
     * 透明状态完成时回调
     *
     * @param drawCompleted <code>true</code> 表示下层 Activities 已经完成重绘
     *                      <code>false</code> 表示等待重绘操作完成的过程超时
     */
    void toTranslucentFinished(boolean drawCompleted);
}
