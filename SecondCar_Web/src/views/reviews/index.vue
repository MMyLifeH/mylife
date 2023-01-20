<template>
    <div class="reviews">
        <div class="button">
            <div>
                <el-button type="primary" @click="showAdd()">添加评论
                </el-button>
            </div>
            <div class="queryRight">
                <el-input class="input" v-model="queryForm.rv_content" placeholder="请输入内容">
                </el-input>
                <el-button type="primary" style="margin-right:40px" @click="fetchData()">搜索</el-button>
            </div>
        </div>

        <el-table class="middle" :data="list" border stripe highlight-current-row>
            <el-table-column label="序号" align="center" width="100px" prop="rv_id"></el-table-column>
            <el-table-column label="评论内容" align="center" width="600px" prop="rv_content"></el-table-column>
            <el-table-column label="发布日期" align="center" width="400px" prop="re_date"></el-table-column>
            <el-table-column label="操作" align="center" prop="caozuo">
                <template slot-scope="scope">
                    <el-button type="primary" @click="updateShow(scope.row)">修改</el-button>

                    <el-button type="danger" @click="deleteReviews(scope.row.rv_id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page"
            :page-sizes="[10, 15, 30, 50]" :page-size="itemsPerPage" layout="total, sizes, prev, pager, next, jumper"
            :total="total" class="bottom" background>
        </el-pagination>
        <!-- 添加  -->
        <el-dialog title="编辑" :visible.sync="dialogFormVisible">
            <el-form :model="addform" :rules="Rules" ref="addform">
                <el-form-item prop="rv_content" class="addshuru" label="评论内容" :label-width="formLabelWidth">
                    <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" v-model="addform.rv_content"
                        autocomplete="off"></el-input>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addreviews('addform')">确 定</el-button>
            </div>

        </el-dialog>

        <!-- 修改 -->
        <el-dialog title="编辑" :visible.sync="dialogForm">
            <el-form :model="updateform" :rules="Rules" ref="updateform">
                <el-form-item prop="rv_content" class="addshuru" label="评论内容" :label-width="formLabelWidth">
                    <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" v-model="updateform.rv_content"
                        autocomplete="off"></el-input>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogForm = false">取 消</el-button>
                <el-button type="primary" @click="updateReviews('updateform')">确 定</el-button>
            </div>

        </el-dialog>


    </div>
</template>

<script>
/* 导入 dao.js中默认成员 */
import dao from "@/api/dao";

export default {
    data() {
        return {
            dialogForm: false,
            queryForm: {
                rv_content: "",
            },
            /* 当前页 */
            page: 1,
            /* 每页记录数 */
            itemsPerPage: 10,
            /* 总记录数 */
            total: 0,
            /* 分页数据 */
            list: [],
            dialogFormVisible: false,
            form: {
                name: "",
            },
            addform: {
                rv_content: "",
            },
            updateform: {
                rv_content: "",
            },
            Rules: {
                rv_content: [
                    { required: true, message: '请输入评论内容', trigger: 'blur' },
                ],
            },
        };
    },
    methods: {
        showAdd() {
            this.addform = {}
            this.dialogFormVisible = true
        },
        // 查询
        fetchData() {
            /* 当异步发送成功,则执行then,这里的response就是形参
                  表示服务器端返回的数据 */
            dao.queryReviewsInfo(this.page, this.itemsPerPage, this.queryForm.rv_content).then((response) => {
                console.log(response);
                /* 获取分页数据 */
                this.list = response.data.rows;
                /* 获取总记录数 */
                this.total = response.data.total;
            });
        },
        // 添加
        addreviews(addForm) {
            this.$refs[addForm].validate((valid) => {
                if (valid) {
                    this.$http.post("/reviews/addReviews", this.addform)
                    this.$message.success("添加成功")
                    this.fetchData()
                    this.$refs[addForm].resetFields()
                    this.dialogFormVisible = false;
                } else {
                    this.$message.error("表单参数有漏填项，请检查后重新输入！")
                    return false;
                }
            });
        },
        // 删除
        deleteReviews(rv_id) {
            this.$confirm('确认删除此评论吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$http.get("http://localhost:9999/users/reviews/deleteReviews?id=" + rv_id).then((res) => {
                    this.$message(res.data)
                    this.fetchData()

                })

            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        // 修改
        updateReviews(updateForm) {
            this.$refs[updateForm].validate((valid) => {
                if (valid) {
                    this.$http.post("/reviews/updateReviews", this.updateform).then(res => {

                        this.$message.success("修改成功")
                        this.fetchData()
                        this.dialogForm = false;
                    })
                } else {
                    this.$message.error("表单参数有漏填项，请检查后重新输入！")
                    return false;
                }
            });
        },
        // 显示原数据
        updateShow(row) {
            Object.assign(this.updateform, row)

            this.dialogForm = true

        },
        /* 每页记录数如果更改,则执行此函数 val形参就表示
            更改后的每页记录数 */
        handleSizeChange(val) {
            /* 更新每页记录数 */
            this.itemsPerPage = val;
            /* 回显 重新查询因为每页记录数更改了 */
            this.fetchData();
        },
        /* 当前页如果更改,执行此函数,val形参就是更改后的当前页的值 */
        handleCurrentChange(val) {
            /* 更新当前页 */
            this.page = val;
            /* 回显 重新查询因为当前页更改了 */
            this.fetchData();
        },
    },
    /* 此函数在Vue实例或者组件最早获得数据时执行, */
    created() {
        /* 调用函数 */
        this.fetchData();
    },
    /* 过滤器 */
    filters: {
        myFilter(val) {
            const roleObj = roleOptions.find((option) => option.id === val);
            return roleObj ? roleObj.type : "";
        },
    },
};
</script>

<style scoped>
/* 设置表格样式 */
.reviews .middle {
    /* 开启弹性盒子后,此参数设置表示,如果外侧容器存在空间,则放大,
        如果外侧容器空间不够,则缩小 */
    flex: 1;
}

/* 设置分页样式 */
.reviews .bottom {
    text-align: center;
    height: 5vh;
}

.button {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}

.queryRight {
    display: flex;
    flex-direction: row;
}
</style>