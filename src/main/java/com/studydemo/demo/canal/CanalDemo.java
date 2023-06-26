package com.studydemo.demo.canal;

import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;

import java.net.InetSocketAddress;
import java.util.List;

public class CanalDemo {

    public static void main(String[] args) {
        // 创建Canal连接器
        CanalConnector connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress("localhost", 11111),  // Canal Server的地址和端口
                "example",  // instance名称
                "", "");

        try {
            connector.connect();
            connector.subscribe(".*\\..*"); // 监控所有库、所有表
            connector.rollback();

            while (true) {
                Message message = connector.getWithoutAck(100); // 每次获取100条数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId != -1 && size > 0) {
                    // 处理Canal数据
                    processEntries(message.getEntries());
                }
                connector.ack(batchId); // 提交确认
            }
        } finally {
            connector.disconnect();
        }
    }

    private static void processEntries(List<CanalEntry.Entry> entries) {
        for (CanalEntry.Entry entry : entries) {
            if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA) {
                try {
                    CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                    for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
                        if (rowChange.getEventType() == CanalEntry.EventType.UPDATE) {
                            printUpdatedData(rowData.getBeforeColumnsList(), rowData.getAfterColumnsList());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void printUpdatedData(List<CanalEntry.Column> beforeColumns, List<CanalEntry.Column> afterColumns) {
        System.out.println("Updated data:");
        for (CanalEntry.Column column : afterColumns) {
            System.out.println(column.getName() + ": " + column.getValue());
        }
        System.out.println();
    }
}

