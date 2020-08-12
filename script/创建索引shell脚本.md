# 笔记信息表创建索引-唯一联合索引
db.c_note_info.ensureIndex({"userNo":1, "primaryDirectory":1, "secondaryDirectory":1, "fileName":1, "status":1}, {"unique":true});

# 文件信息表创建字段
db.c_file_upload_record.ensureIndex({"userNo":1});
db.c_file_upload_record.ensureIndex({"fileId":1}, {"unique":true});

# 用户信息表创建索引
db.c_user_info.ensureIndex({"userNo":1, "status":1}, {"unique":true});

# 账户信息表创建索引
db.c_account_info.ensureIndex({"userNo":1, "status":1});

