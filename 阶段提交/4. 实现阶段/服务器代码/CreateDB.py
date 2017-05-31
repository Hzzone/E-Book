#!/usr/bin/env python
# -*- coding: utf-8 -*-
import psycopg2

config = {
    'host': '127.0.0.1',
    'port': 5432,
    'user': 'Hzzone',
    'passwd': '',
    'charset':'utf8mb4',
    'cursorclass':psycopg2.cursors.DictCursor
    }
conn = psycopg2.connect(**config)
conn.autocommit(1)
cursor = conn.cursor()

try:
    # 创建数据库
    DB_NAME = 'ebook'
    cursor.execute('DROP DATABASE IF EXISTS %s' %DB_NAME)
    cursor.execute('CREATE DATABASE IF NOT EXISTS %s' %DB_NAME)
    conn.select_db(DB_NAME)

    #创建表
    TABLE_NAME = 'user'
    cursor.execute('CREATE TABLE %s(user_account varchar(100) primary key,user_name varchar(100), user_password varchar(100))' %TABLE_NAME)

    # 批量插入纪录
    values = []
    for i in range(20):
        values.append((str(i)+'@qq.com' ,'kk'+str(i), str(i)))
    cursor.executemany('INSERT INTO user values(%s,%s,%s)',values)

    # 查询数据条目
    count = cursor.execute('SELECT * FROM %s' %TABLE_NAME)
    print 'total records:', cursor.rowcount

    # 获取表名信息
    desc = cursor.description
    print "%s %3s" % (desc[0][0], desc[1][0])

    cursor.scroll(10,mode='absolute')
    results = cursor.fetchall()
    for result in results:
        print result

except:
    import traceback
    traceback.print_exc()
    # 发生错误时会滚
    conn.rollback()
finally:
    # 关闭游标连接
    cursor.close()
    # 关闭数据库连接
    conn.close()