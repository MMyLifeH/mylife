<template>
  <div class="user">
    <div class="button">
      <div>
        <el-button type="primary" @click="showAdd()">添加用户信息 </el-button>
      </div>
      <div class="queryRight">
        <el-input
          class="input"
          v-model="queryForm.u_name"
          placeholder="请输入内容"
        >
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
      :data="list"
      border="1px"
      style="width: 100%"
      :row-class-name="tableRowClassName"
      :default-sort = "{prop: 'u_state,role', order: 'descending'}"
    >
      <el-table-column prop="id" label="用户名" width="80"> </el-table-column>
      <el-table-column prop="u_name" label="姓名" width="150"> </el-table-column>
      <el-table-column prop="password" label="密码" width="150">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="100"> </el-table-column>
      <el-table-column prop="salary" label="总工资" width="200">
      </el-table-column>
      <el-table-column prop="position" label="职位" width="150">
      </el-table-column>
      <el-table-column prop="department" label="部门" width="100">
      </el-table-column>
      <el-table-column prop="u_state" label="状态" width="80" sortable>
      </el-table-column>
      <el-table-column prop="role" label="权限" width="80" sortable> </el-table-column>

      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="primary" plain @click="updateShow(scope.row)"
            >修改</el-button
          >
          <el-button type="danger" plain @click="deleteUser(scope.row.id)"
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
    <!-- 添加  -->
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form :model="addform" :rules="Rules" ref="addform">
        <el-form-item label="用户名" :label-width="formLabelWidth" prop="id">
          <el-input v-model="addform.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="u_name">
          <el-input v-model="addform.u_name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item
          label="密码"
          :label-width="formLabelWidth"
          prop="password"
        >
          <el-input v-model="addform.password" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="性别" :label-width="formLabelWidth" prop="sex">
          <el-input v-model="addform.sex" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item
          label="岗位"
          :label-width="formLabelWidth"
          prop="position"
        >
          <el-input v-model="addform.position" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="部门"
          :label-width="formLabelWidth"
          prop="department"
        >
          <el-input v-model="addform.department" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="状态" :label-width="formLabelWidth" prop="u_state">
          <el-input v-model="addform.u_state" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="权限" :label-width="formLabelWidth" prop="role">
          <el-input v-model="addform.role" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="adduser('addform')">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改 -->
    <el-dialog title="修改" :visible.sync="dialogFormVisible1">
      <el-form :model="updateform" :rules="orderRules" ref="updateform">
        <el-form-item
          prop="u_name"
          class="addshuru"
          label="姓名"
          :label-width="formLabelWidth"
        >
          <el-input v-model="updateform.u_name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item
          prop="password"
          class="addshuru"
          label="密码"
          :label-width="formLabelWidth"
        >
          <el-input v-model="updateform.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          prop="sex"
          class="addshuru"
          label="性别"
          :label-width="formLabelWidth"
        >
          <el-input v-model="updateform.sex" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          prop="position"
          class="addshuru"
          label="岗位"
          :label-width="formLabelWidth"
        >
          <el-input v-model="updateform.position" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          prop="department"
          class="addshuru"
          label="部门"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="updateform.department"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          prop="u_state"
          class="addshuru"
          label="状态"
          :label-width="formLabelWidth"
        >
          <el-input v-model="updateform.u_state" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          prop="role"
          class="addshuru"
          label="权限"
          :label-width="formLabelWidth"
        >
          <el-input v-model="updateform.role" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="info" @click="dialogFormVisible1 = false"
          >取 消</el-button
        >
        <el-button type="primary" @click="updateorder('updateform')"
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
      // mypic: require('@/assets/k.jpg'),
      dialogFormVisible1: false,
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
      dialogFormVisible: false,
      form: {
        u_name: "",
      },
      addform: {
        id: "",
        u_name: "",
        password: "",
        sex: "",
        salary: "",
        position: "",
        department: "",
        u_state:"",
        role: "",
        // pic: "",
      },
      updateform: {
        u_name: "",
        password: "",
        sex: "",
        salary: "",
        position: "",
        department: "",
        u_state:"",
        role: "",
      },
      Rules: {
        id: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        u_name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
        sex: [
          {
            required: true,
            message: "请输入性别",
            trigger: "blur",
          },
        ],
        position: [
          {
            required: true,
            message: "请输入岗位",
            trigger: "blur",
          },
        ],
        department: [
          {
            required: true,
            message: "请输入部门",
            trigger: "blur",
          },
        ],
        u_state: [
          {
            required: true,
            message: "请输入状态",
            trigger: "blur",
          },
        ],
        role: [
          {
            required: true,
            message: "请输入角色权限",
            trigger: "blur",
          },
        ],
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
    // 添加
    adduser(addForm) {
      this.$refs[addForm].validate((valid) => {
        if (valid) {
          this.$http.post("/login/adduser", this.addform).then((res) => {
            this.$message.success("添加成功");
            this.fetchData();
            this.$refs[addForm].resetFields();
            this.dialogFormVisible = false;
          });
        } else {
          this.$message.error("表单参数有漏填项，请检查后重新输入！");
          return false;
        }
      });
    },
    // 修改
    updateorder(updateForm) {
      this.$refs[updateForm].validate((valid) => {
        if (valid) {
          this.$http.post("/login/updateUsers", this.updateform).then((res) => {
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
    deleteUser(id) {
      this.$confirm("确认删除用户吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get("http://localhost:9999/users/login/deleteUsers?id=" + id)
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
</style>