<template>
  <div class="user">
    <el-table
      :data="list"
      border="1px"
      style="width: 100%"
      :row-class-name="tableRowClassName"
    >
      <el-table-column prop="id" label="用户名" width="80"> </el-table-column>
      <el-table-column prop="u_name" label="姓名" width="150">
      </el-table-column>
      <el-table-column prop="password" label="密码" width="150">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="100"> </el-table-column>
      <el-table-column prop="salary" label="总工资" width="200">
      </el-table-column>
      <el-table-column prop="position" label="职位" width="150">
      </el-table-column>
      <el-table-column prop="department" label="部门" width="100">
      </el-table-column>
      <el-table-column prop="u_state" label="状态" width="100" sortable>
      </el-table-column>
      <el-table-column prop="role" label="权限" width="100"> </el-table-column>

      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <div
            class="shensu"
            :style="scope.row.u_state === 0 ? 'background-color: green;' : ''"
            @click="updateStatus(scope.row)"
          >
            <div
              class="el-icon-search iconState"
              style="margin-top: 2px"
              :style="scope.row.u_state === 0 ? 'color:white' : ''"
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
      // mypic: require('@/assets/k.jpg'),

      queryForm: {
        u_name: "",
      },
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
      let status = row.u_state === 1 ? 0 : 1;
      this.$http
        .get("/login/updateState", { params: { id: row.id, u_state: status } })
        .then((res) => {
          if (res.data.flag == "success") {
            this.$message.success(res.data.msg);
            this.fetchData();
          }
        });
    },
    // 查询
    fetchData() {
      /* 当异步发送成功,则执行then,这里的response就是形参
                  表示服务器端返回的数据 */
      dao
        .queryUsersInfo(this.page, this.itemsPerPage, this.queryForm.u_name)
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
/* 设置表格样式 */
.user .middle {
  /* 开启弹性盒子后,此参数设置表示,如果外侧容器存在空间,则放大,
        如果外侧容器空间不够,则缩小 */
  flex: 1;
}

/* 设置分页样式 */
.user .bottom {
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

.shensu {
  border: 1px solid;
  width: 50px;
  height: 30px;
  border-radius: 20px;
}

.iconState {
  font-size: 25px;
  color: #7a7676;
  margin-left: 12px;
}
</style>