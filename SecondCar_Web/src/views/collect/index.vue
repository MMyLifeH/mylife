<template>
    <div class="Vmanage">
        <!-- 增加车辆信息 -->

        <el-table :data="list" style="width: 100%" :row-class-name="tableRowClassName">
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



            <el-table-column fixed="right" label="" width="200">
                <template slot-scope="scope">

                    <div class="car" :style="scope.row.collectstate === 1 ? 'background-color: rgb(234, 228, 49);' : ''"
                        @click="updateCarc(scope.row)">
                        <div class="el-icon-star-off iconCar" :style="scope.row.collectstate === 1 ? 'color:white' : ''">
                        </div>
                    </div>
                </template>



            </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page"
            :page-sizes="[5, 10, 15, 20]" :page-size="itemsPerPage" layout="total, sizes, prev, pager, next, jumper"
            :total="total" class="bottom" background>
        </el-pagination>
    </div>
</template>

<script>
export default {
    data() {
        return {


            mypic: require('@/assets/k.jpg'),




            handleEdit: false,
            page: 1,
            itemsPerPage: 5,
            total: 0,
            list: [],
            param: {
                pageNumber: 1,
                pageSize: 5,
            },
            dialogTableVisible: false,
            dialogFormVisible: false,
            form: {
                vehiclename: "",
                // vehicleprice: "",
                // vehiclecon: "",
                // delivery: false,
                // type: [],
                // resource: '',
                // desc: ''
            },
            formLabelWidth: "120px",
        };
    },
    methods: {
        updateCarc(row) {
            let status = row.collectstate === 1 ? 0 : 1
            this.$http.get('/vehicle/updateCarc',
                { params: { vehicleid: row.vehicleid, collectstate: status } }).then(res => {
                    if (res.data.flag === 'success') {
                        this.$message.success(res.data.msg)
                        this.fetchData()
                    } else {
                        this.$message.error("加入收藏夹失败")
                    }


                })
        },
        fetchData() {
            /* 当异步发送成功,则执行then,这里的response就是形参
                  表示服务器端返回的数据 */
            this.$http("/vehicle/querySome2", { params: this.param }).then(
                (response) => {
                    console.log(response);
                    /* 获取分页数据 */
                    this.list = response.data.rows;
                    /* 获取总记录数 */
                    this.total = response.data.total;
                }
            );
        },
        handleSizeChange(val) {
            /* 更新每页记录数 */
            this.param.pageSize = val;
            /* 回显 重新查询因为每页记录数更改了 */
            this.fetchData();
        },
        /* 当前页如果更改,执行此函数,val形参就是更改后的当前页的值 */
        handleCurrentChange(val) {
            /* 更新当前页 */
            this.param.pageNumber = val;
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
.button {
    background-color: white;
}

.table {
    width: 100%;
    height: 100%;
    background-color: #ddd;
    /* 开启弹性盒子 */
    display: flex;
    /* 盒子内部的元素从上往下排列 */
    flex-direction: column;
}

/* 设置表格样式 */
.Vmanage .middle {
    /* 开启弹性盒子后,此参数设置表示,如果外侧容器存在空间,则放大,
        如果外侧容器空间不够,则缩小 */
    flex: 1;
}

/* 设置分页样式 */
.Vmanage .bottom {
    text-align: center;
    height: 5vh;
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

/* */
</style>