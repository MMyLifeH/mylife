<template>
  <div class="ddgl">
    <div class="button">
      <div>
        <el-button type="primary" @click="showAdd()">添加报销申请单</el-button>
      </div>
      <div class="queryRight">
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

    <el-table
      class="middle"
      :data="list"
    >
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
        width="100px"
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
      <el-table-column label="操作" align="center" prop="caozuo">
        <template slot-scope="scope">
          <el-button type="danger" plain @click="deleteReimburse(scope.row.rid)"
            >删除</el-button
          >
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
    <!-- 添加 -->
    <el-dialog title="添加工资明细" :visible.sync="dialogFormVisible">
      <el-form :model="addform" :rules="Rules" ref="addform">
        <el-form-item
          label="选择日期"
          :label-width="formLabelWidth"
          prop="r_time"
        >
          <el-date-picker
            v-model="addform.r_time"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
            style="padding-left: 4%"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item
          prop="office"
          label="办公用品费"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model.number="addform.office"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item prop="room" label="住宿费" :label-width="formLabelWidth">
          <el-input v-model.number="addform.room" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item prop="eat" label="餐补费" :label-width="formLabelWidth">
          <el-input v-model="addform.eat" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item
          prop="gasoline"
          label="加油费"
          :label-width="formLabelWidth"
        >
          <el-input v-model="addform.gasoline" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item
          prop="sid"
          label="工资条编号"
          :label-width="formLabelWidth"
        >
          <el-input v-model="addform.sid" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="info" @click="dialogFormVisible = false"
          >取消</el-button
        >
        <el-button type="primary" @click="addreimburse('addform')"
          >添加</el-button
        >
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
      dialogFormVisible1: false,
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
      dialogFormVisible: false,
      form: {
        rid: "",
      },
      addform: {
        office: "",
        room: "",
        eat: "",
        gasoline: "",
        r_time: "",
        sid: "",
      },
      Rules: {
        office: [
          { required: true, message: "请输入办公用品费", trigger: "blur" },
        ],
        room: [{ required: true, message: "请输入住宿费", trigger: "blur" }],
        eat: [{ required: true, message: "请输入餐补费", trigger: "blur" }],
        gasoline: [
          { required: true, message: "请输入加油费", trigger: "blur" },
        ],
        r_time: [{ required: true, message: "请选择日期", trigger: "blur" }],
        sid: [{ required: true, message: "请输入工资条编号", trigger: "blur" }],
      },
    };
  },

  methods: {
    showAdd() {
      this.addform = {};
      this.dialogFormVisible = true;
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
    addreimburse(addForm) {
      this.$refs[addForm].validate((valid) => {
        if (valid) {
          this.$http.post("/reimburse/addReimburse", this.addform);
          this.$message.success("添加成功");
          this.fetchData();
          this.$refs[addForm].resetFields();
          this.dialogFormVisible = false;
        } else {
          this.$message.error("表单参数有漏填项，请检查后重新输入！");
          return false;
        }
      });
    },

    // 删除
    deleteReimburse(id) {
      this.$confirm("确认删除此订单吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:9999/users/reimburse/deleteReimburse?id=" + id
            )
            .then((res) => {
              this.$message(res.data);
              this.fetchData();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
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
</style>
