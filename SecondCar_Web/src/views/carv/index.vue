<template>
    <div class="vehicle">
        <div class="button">
            <div class="queryRight">
                <el-input class="input" v-model="queryForm.vehiclename" placeholder="请输入内容">
                </el-input>
                <el-button type="primary" style="margin-right:50px" @click="fetchData()">搜索</el-button>
            </div>
        </div>

        <el-table :data="list" style="width: 100%" border="2px" :row-class-name="tableRowClassName">
            <el-table-column prop="vehicleid" label="车辆编号" width="180">
            </el-table-column>
            <el-table-column prop="vehiclename" label="车辆型号" width="250">
            </el-table-column>
            <el-table-column prop="vehicleprice" label="车辆价格（万元）" width="180">
            </el-table-column>
            <el-table-column prop="vehiclecon" label="车况" width="250">
            </el-table-column>


            <el-table-column prop="pic" label="图片" width="200">
                <template slot-scope="scope">
                    <el-image :src="scope.row.pic ? 'http://localhost:9999/users' + scope.row.pic : mypic">
                    </el-image>
                </template>
            </el-table-column>

            <el-table-column fixed="right" label="" width="250">
                <template slot-scope="scope">
                    <div class="car" :style="scope.row.shoppingstate === 1 ? 'background-color: green;' : ''"
                        @click="updateCarStatus(scope.row)">
                        <div class="el-icon-shopping-cart-2 iconCar"
                            :style="scope.row.shoppingstate === 1 ? 'color:white' : ''"></div>
                    </div>

                    <div class="car" :style="scope.row.collectstate === 1 ? 'background-color: rgb(234, 228, 49);' : ''"
                        @click="updateCarc(scope.row)">
                        <div class="el-icon-star-off iconCar"
                            :style="scope.row.collectstate === 1 ? 'color:white' : ''">
                        </div>
                    </div>

                    <el-button type="primary" round  @click="showAdd()" size="mini">评论
                    
                    </el-button>
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

    </div>
</template>

<script>
/* 导入 dao.js中默认成员 */
import dao from "@/api/dao";

export default {
    data() {
        return {
            mypic: require('@/assets/k.jpg'),
            dialogForm: false,
            queryForm: {
                vehiclename: "",
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
                vehiclename: "",
            },
            addform: {
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
        // 评论
        addreviews(addForm) {
            this.$refs[addForm].validate((valid) => {
                if (valid) {
                    this.$http.post("/reviews/addReviews", this.addform)
                    // this.$message.success("添加成功")
                    this.fetchData()
                    this.$refs[addForm].resetFields()
                    this.dialogFormVisible = false;
                    this.$router.push({ path: "qreview" });
                } else {
                    this.$message.error("评论内容不能为空")
                    return false;
                }
            });
            
        },
        updateCarStatus(row) {
            let status = row.shoppingstate === 1 ? 0 : 1
            this.$http.get('vehicle/updateCarShoppingStatus',
                { params: { vehicleid: row.vehicleid, shoppingstate: status } }).then(res => {
                    if (res.data.flag === 'success') {
                        this.$message.success(res.data.msg)
                        this.fetchData()
                    } else {
                        this.$message.error("加入购物车失败")
                    }


                })
        },
        updateCarc(row) {
            let status = row.collectstate === 1 ? 0 : 1
            this.$http.get('vehicle/updateCarc',
                { params: { vehicleid: row.vehicleid, collectstate: status } }).then(res => {
                    if (res.data.flag === 'success') {
                        this.$message.success(res.data.msg)
                        this.fetchData()
                    } else {
                        this.$message.error("加入收藏夹失败")
                    }


                })
        },
        // 查询
        fetchData() {
            /* 当异步发送成功,则执行then,这里的response就是形参
                  表示服务器端返回的数据 */
            dao.queryVehicles(this.page, this.itemsPerPage, this.queryForm.vehiclename).then((response) => {
                console.log(response);
                /* 获取分页数据 */
                this.list = response.data.rows;
                /* 获取总记录数 */
                this.total = response.data.total;
            });
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
.vehicle .middle {
    /* 开启弹性盒子后,此参数设置表示,如果外侧容器存在空间,则放大,
        如果外侧容器空间不够,则缩小 */
    flex: 1;
}

/* 设置分页样式 */
.vehicle .bottom {
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

.button {
    background-color: white;
}

.car {
    border: 1px solid;
    width: 50px;
    height: 30px;
    border-radius: 20px
}

.iconCar {

    font-size: 25px;
    color: #7a7676;
    margin-left: -3px;
}

/deep/.el-table td.el-table__cell div {
    display: flex;
    justify-content: space-around;
}
</style>