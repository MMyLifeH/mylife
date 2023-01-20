<template>
  <div class="ddgl">
    <div class="button">
      <div>
        <el-button type="primary" @click="showAdd()">添加工资明细 </el-button>
      </div>
      <div class="queryRight">
        <el-input class="input" v-model="queryForm.id" placeholder="请输入内容">
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

    <el-table class="middle" :data="list" border stripe highlight-current-row>
      <el-table-column
        label="工资条编号"
        prop="sid"
        align="center"
        width="100px"
      ></el-table-column>
      <!-- prop:表示对应字段名 -->
      <el-table-column
        label="基本工资"
        align="center"
        width="200px"
        prop="wage"
      ></el-table-column>
      <el-table-column
        label="奖金"
        align="center"
        width="200px"
        prop="welfare"
      ></el-table-column>
      <el-table-column
        label="报销"
        align="center"
        width="200px"
        prop="reimburse"
      ></el-table-column>
      <el-table-column
        label="时间"
        align="center"
        width="200px"
        prop="s_time"
        sortable
      ></el-table-column>
      <el-table-column
        label="员工编号"
        align="center"
        width="200px"
        prop="id"
      ></el-table-column>
      <el-table-column label="操作" align="center" prop="caozuo">
        <template slot-scope="scope">
          <el-button size="primary" plain @click="updateShow(scope.row)"
            >修改</el-button
          >

          <el-button type="danger" plain @click="deleteSalary(scope.row.id)"
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
          prop="s_time"
        >
          <el-date-picker
            v-model="addform.s_time"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
            style="padding-left: 4%"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item
          prop="wage"
          label="基本工资"
          :label-width="formLabelWidth"
        >
          <el-input v-model.number="addform.wage" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item prop="welfare" label="奖金" :label-width="formLabelWidth">
          <el-input
            v-model.number="addform.welfare"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item
          prop="reimburse"
          label="报销"
          :label-width="formLabelWidth"
        >
          <el-input v-model="addform.reimburse" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item prop="id" label="员工编号" :label-width="formLabelWidth">
          <el-input v-model="addform.id" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="info" @click="dialogFormVisible = false"
          >取消</el-button
        >
        <el-button type="primary" @click="addsalary('addform')">添加</el-button>
      </div>
    </el-dialog>
    <!-- 修改 -->
    <el-dialog title="修改工资明细" :visible.sync="dialogFormVisible1">
      <el-form :model="updateform" :rules="orderRules" ref="updateform">
        <el-form-item
          label="选择日期"
          :label-width="formLabelWidth"
          prop="s_time"
        >
          <el-date-picker
            v-model="updateform.s_time"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
            style="padding-left: 4%"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item
          prop="wage"
          class="addshuru"
          label="基本工资"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model.number="updateform.wage"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item
          prop="welfare"
          class="addshuru"
          label="奖金"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model.number="updateform.welfare"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item
          prop="reimburse"
          class="addshuru"
          label="报销"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="updateform.reimburse"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item
          prop="id"
          class="addshuru"
          label="员工编号"
          :label-width="formLabelWidth"
        >
          <el-input v-model="updateform.id" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="info" @click="dialogFormVisible1 = false"
          >取 消</el-button
        >
        <el-button type="primary" @click="updatesalary('updateform')"
          >确 定</el-button
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
        id: "",
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
        wage: "",
      },
      addform: {
        wage: "",
        welfare: "",
        reimburse: "",
        s_time:"",
        id: "",
      },
      updateform: {
        wage: "",
        welfare: "",
        reimburse: "",
        s_time:"",
        id: "",
      },
      Rules: {
        s_time: [{ required: true, message: "请选择日期", trigger: "blur" }],
        wage: [{ required: true, message: "请输入基本工资", trigger: "blur" }],
        welfare: [
          { required: true, message: "请输入奖金福利", trigger: "blur" },
        ],
        reimburse: [
          { required: true, message: "请输入报销金额", trigger: "blur" },
        ],
        id: [{ required: true, message: "请输入员工编号", trigger: "blur" }],
      },
    };
  },

  methods: {
    showAdd() {
      this.addform = {};
      this.dialogFormVisible = true;
    },
    updateShow(row) {
      Object.assign(this.updateform, row);

      this.dialogFormVisible1 = true;
    },
    /* 此函数用来调用方法发送异步请求 */
    fetchData() {
      /* 当异步发送成功,则执行then,这里的response就是形参
            表示服务器端返回的数据 */
      dao
        .querySalaryInfo(this.page, this.itemsPerPage, this.queryForm.id)
        .then((response) => {
          console.log(response);
          /* 获取分页数据 */
          this.list = response.data.rows;
          /* 获取总记录数 */
          this.total = response.data.total;
        });
    },
    addsalary(addForm) {
      this.$refs[addForm].validate((valid) => {
        if (valid) {
          this.$http.post("/salary/addSalary", this.addform);
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

    //  修改
    updatesalary(updateForm) {
      this.$refs[updateForm].validate((valid) => {
        if (valid) {
          this.$http
            .post("/salary/updateSalary", this.updateform)
            .then((res) => {
              console.log(this.updateform);
              this.$message.success("修改成功");
              this.fetchData();
              this.dialogFormVisible1 = false;
            });

          // this.$refs[updateForm].resetFields()
        } else {
          this.$message.error("表单参数有漏填项，请检查后重新输入！");
          return false;
        }
      });
    },

    // 删除
    deleteSalary(id) {
      this.$confirm("确认删除此订单吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get("http://localhost:9999/users/salary/deleteSalary?id=" + id)
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
