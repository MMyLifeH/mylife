<template>
    <div class="reviews">

        <el-table class="middle" :data="list" border stripe highlight-current-row>
            <el-table-column label="序号" align="center" width="90px" prop="rv_id"></el-table-column>
            <el-table-column label="评论内容" align="center" width="850px" prop="rv_content"></el-table-column>
            <el-table-column label="发布日期" align="center" width="380px" prop="re_date"></el-table-column>
        </el-table>

        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page"
            :page-sizes="[10, 15, 30, 50]" :page-size="itemsPerPage" layout="total, sizes, prev, pager, next, jumper"
            :total="total" class="bottom" background>
        </el-pagination>
      
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
        };
    },
    methods: {
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