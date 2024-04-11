package com.datn.watch.notification.repository;

import com.pet.market.api.common.utils.AuthUtils;
import com.pet.market.api.common.utils.StringUtils;
import com.pet.market.api.notification.model.dto.NotificationDto;
import com.pet.market.api.notification.model.entity.Notification;
import org.apache.ibatis.jdbc.SQL;

import java.util.Arrays;

public class NotificationSqlBuilder {
    private static final String TABLE_NAME = "TB_NOTIFICATION";

    public String getAll(NotificationDto.ListReq req) {
        return new SQL() {{
            SELECT("`ID`, `SENDER`, `RECEIVER`, SUBJECT, `CONTENT`,`IS_READ`,`TARGET_ID`,`TYPE`,`URL`");
            FROM(TABLE_NAME);
            WHERE("is_deleted = " + 0);
            AND().WHERE("RECEIVER = " + "'" + AuthUtils.getUserId() + "'");
            if (!StringUtils.isNullOrEmpty(req.getOrderBy()) && req.getOrderBy().equals("DESC")){
                ORDER_BY("created_date DESC");
            }
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String getById(Long id) {
        return new SQL() {{
            SELECT("`ID`, `SENDER`, `RECEIVER`, SUBJECT, `CONTENT`,`IS_READ`,`TARGET_ID`,`TYPE`,`URL`");
            FROM(TABLE_NAME);
            WHERE("ID = #{id}");
        }}.toString();
    }

    public String findAll(NotificationDto.ListReq req) {
        return new SQL() {{
            SELECT("`ID`, `SENDER`, `RECEIVER`, SUBJECT, `CONTENT`,`IS_READ`,`TARGET_ID`,`TYPE`,`URL`");
            FROM(TABLE_NAME);
            WHERE("is_deleted = " + 0);
            AND().WHERE("RECEIVER = " + "'" + AuthUtils.getUserId() + "'");
        }}.toString();
    }

    public String count(NotificationDto.ListReq req) {
        return new SQL() {{
            SELECT("count(id)");
            FROM(TABLE_NAME);
            WHERE("is_deleted = " + 0);
            AND().WHERE("RECEIVER = " + "'" + AuthUtils.getUserId() + "'");
        }}.toString();
    }

    public String delete(Long id) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            SET("is_deleted = 1");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String deletes(NotificationDto.ListIdReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            SET("is_deleted = 1");
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ")");
        }}.toString();
    }

    public String updateStatus(NotificationDto.StatusReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String save(Notification object) {
        return new SQL() {{
            INSERT_INTO(TABLE_NAME);
            VALUES("created_by, created_date", "#{createdBy},now()");
            VALUES("updated_by, updated_date", "#{lastModifiedBy},now()");
            VALUES("SENDER", "#{sender}");
            VALUES("RECEIVER", "#{receiver}");
            VALUES("SUBJECT", "#{subject}");
            VALUES("CONTENT", "#{content}");
            VALUES("TYPE", "#{type}");
            VALUES("TARGET_ID", "#{targetId}");
            VALUES("URL", "#{url}");
        }}.toString();
    }

    public String update(Notification object) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = now()");
            SET("SENDER = #{sender}");
            SET("RECEIVER = #{receiver}");
            SET("SUBJECT = #{subject}");
            SET("CONTENT = #{content}");
            SET("IS_READ = #{isRead}");
            WHERE("ID = #{id}");
        }}.toString();
    }

    public String getMyUnreadNotificationCount(NotificationDto.ListReq req) {
        return new SQL() {{
            SELECT("count(id)");
            FROM(TABLE_NAME);
            WHERE("is_deleted = " + 0);
            AND().WHERE("RECEIVER = " + "'" + AuthUtils.getUserId() + "'");
            AND().WHERE("IS_READ != " + 1);
        }}.toString();
    }

    public String markMyNotificationRead(NotificationDto.ListIdReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("IS_READ = 1");
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ")");
        }}.toString();
    }
    public String getByIds(NotificationDto.ListIdReq req) {
        return new SQL() {{
            SELECT("`ID`, `SENDER`, `RECEIVER`, SUBJECT, `CONTENT`,`IS_READ`,`TARGET_ID`,`TYPE`,`URL`");
            FROM(TABLE_NAME);
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ")");
        }}.toString();
    }
}
