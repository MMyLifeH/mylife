<template>
  <div class="ddgl">
    <div class="button">
      <div class="queryRight" style="margin-left: 1050px">
        <el-input
          class="input"
          v-model="queryForm.rid"
          placeholder="请输入内容"
        >
          <!-- <i slot="prefix" class="el-input__icon el-icon-search"></i> -->
        </el-input>
        <el-button
          type="primary"
          style="margin-right: 40px"
          @click="fetchData()"
          >搜索</el-button
        >
      </div>
    </div>

    <el-table class="middle" :data="list">
      <el-table-column
        label="报销单编号"
        prop="rid"
        align="center"
        width="100px"
      ></el-table-column>
      <!-- prop:表示对应字段名 -->
      <el-table-column
        label="办公用品费"
        align="center"
        width="150px"
        prop="office"
      ></el-table-column>
      <el-table-column
        label="住宿费"
        align="center"
        width="150px"
        prop="room"
      ></el-table-column>
      <el-table-column
        label="餐补费"
        align="center"
        width="150px"
        prop="eat"
      ></el-table-column>
      <el-table-column
        label="加油费"
        align="center"
        width="150px"
        prop="gasoline"
      ></el-table-column>
      <el-table-column
        label="状态"
        align="center"
        width="150px"
        prop="state_pass"
        sortable
      ></el-table-column>
      <el-table-column
        label="时间"
        align="center"
        width="150px"
        prop="r_time"
        sortable
      ></el-table-column>
      <el-table-column
        label="工资条编号"
        align="center"
        width="150px"
        prop="sid"
      ></el-table-column>
      <el-table-column fixed="right" label="操作" width="150px">
        <template slot-scope="scope">
          <div
            class="shenhe"
            :style="
              scope.row.state_pass === 1 ? 'background-color: green;' : ''
            "
            @click="updateStatus(scope.row)"
          >
            <div
              class="el-icon-document-checked iconState" style="margin-top:2px"
              :style="scope.row.state_pass === 1 ? 'color:white' : ''"
            ></div>
          </div>
          
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="page"
      :page-sizes="[10, 15, 30, 50]"
      :page-size="itemsPerPage"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      class="bottom"
      background
    >
    </el-pagination>
  </div>
</template>

<script>
/* 导入 dao.js中默认成员 */
import dao from "@/api/dao";

export default {
  data() {
    return {
      queryForm: {
        rid: "",
      },
      formLabelWidth: 200,
      /* 当前页 */
      page: 1,
      /* 每页记录数 */
      itemsPerPage: 10,
      /* 总记录数 */
      total: 0,
      /* 分页数据 */
      list: [],
    };
  },

  methods: {
    updateStatus(row) {
      let status = row.state_pass === 1 ? 0 : 1
      this.$http.get('/reimburse/updateState',
        { params: { rid: row.rid, state_pass: status } }).then(res => {
          if (res.data.flag == 'success') {
            this.$message.success(res.data.msg)
            this.fetchData()
          }
        })
    },
    /* 此函数用来调用方法发送异步请求 */
    fetchData() {
      /* 当异步发送成功,则执行then,这里的response就是形参
            表示服务器端返回的数据 */
      dao
        .queryReimburseInfo(
          this.page,
          this.itemsPerPage,
          this.queryForm.rid
        )
        .then((response) => {
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
.table .middle {
  /* 开启弹性盒子后,此参数设置表示,如果外侧容器存在空间,则放大,
        如果外侧容器空间不够,则缩小 */
  flex: 1;
}

/* 设置分页样式 */
.table .bottom {
  text-align: center;
  height: 5vh;
}

.el-pagination {
  text-align: center;
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

.shenhe {
  border: 1px solid;
  width: 50px;
  height: 30px;
  border-radius: 20px;
}

.iconState {

  font-size: 25px;
  color: #7a7676;
  margin-left: 13px;
}
</style>
