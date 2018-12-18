package com.geyy.http;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author geyy
 * @version $Id: IdleConnectionEvictor.java, v 0.1 2018-12-18 16:09 geyy Exp $
 */
public class IdleConnectionEvictor extends Thread{
    private static final Logger logger = LogManager.getLogger(IdleConnectionEvictor.class);

    private HttpClientConnectionManager connMgr;

    private volatile boolean shutdown;

    public IdleConnectionEvictor(HttpClientConnectionManager connMgr) {
        this.connMgr = connMgr;
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    // 关闭失效的连接
                    //logger.info("now client pool {}",connMgr.getTotalStats().toString());
                    logger.debug("正在关闭无效连接");
                    connMgr.closeExpiredConnections();
                }
            }
        } catch (InterruptedException ex) {
            logger.debug("正在关闭无效连接出现异常:"+ex);
        }
    }

    //关闭清理无效连接的线程
    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
