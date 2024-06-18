<template>
  <el-dialog title="添加菜单" :visible="visible" width="680px" append-to-body @close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col :span="24">
          <el-form-item label="上级菜单" prop="parentId">
            <treeselect
                v-model="form.parentId"
                :options="menuOptions"
                :normalizer="normalizer"
                :show-count="true"
                placeholder="选择上级菜单"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="菜单类型" prop="type">
            <el-radio-group v-model="form.type">
              <el-radio label="M">目录</el-radio>
              <el-radio label="C">菜单</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="菜单图标" prop="icon">
            <el-popover
                placement="bottom-start"
                width="460"
                trigger="click"
                @show="$refs['iconSelect'].reset()"
            >
              <IconSelect ref="iconSelect" @selected="selected" :active-icon="form.icon" />
              <el-input slot="reference" v-model="form.icon" placeholder="点击选择图标" readonly>
                <svg-icon
                    v-if="form.icon"
                    slot="prefix"
                    :svgName="form.icon"
                    style="width: 25px;"
                />
                <i v-else slot="prefix" class="el-icon-search el-input__icon" />
              </el-input>
            </el-popover>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="菜单名称" prop="name">
            <el-input v-model="form.name" placeholder="请输入菜单名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="显示排序" prop="sort">
            <el-input-number v-model="form.sort" controls-position="right" :min="0" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="路由地址" prop="path">
            <el-input v-model="form.path" placeholder="请输入路由地址" />
          </el-form-item>
        </el-col>
        <el-col :span="12" v-if="form.type == 'C'">
          <el-form-item prop="component">
              <span slot="label">
                <el-tooltip content="访问的组件路径，如：`system/user/index`，默认在`views`目录下" placement="top">
                <i class="el-icon-question"></i>
                </el-tooltip>
                组件路径
              </span>
            <el-input v-model="form.component" placeholder="请输入组件路径" />
          </el-form-item>
        </el-col>
        <el-col :span="12" v-if="form.type == 'C'">
          <el-form-item prop="isCache">
              <span slot="label">
                <el-tooltip content="选择是则会被`keep-alive`缓存，需要匹配组件的`name`和地址保持一致" placement="top">
                <i class="el-icon-question"></i>
                </el-tooltip>
                是否缓存
              </span>
            <el-radio-group v-model="form.isCache">
              <el-radio :label="0">缓存</el-radio>
              <el-radio :label="1">不缓存</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item prop="hide">
              <span slot="label">
                <el-tooltip content="选择隐藏则路由将不会出现在侧边栏，但仍然可以访问" placement="top">
                <i class="el-icon-question"></i>
                </el-tooltip>
                显示状态
              </span>
            <el-radio-group v-model="form.hide">
              <el-radio :label="0">显示</el-radio>
              <el-radio :label="1">隐藏</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item prop="status">
              <span slot="label">
                <el-tooltip content="选择停用则路由将不会出现在侧边栏，也不能被访问" placement="top">
                <i class="el-icon-question"></i>
                </el-tooltip>
                菜单状态
              </span>
            <el-radio-group v-model="form.status">
              <el-radio :label="0">正常</el-radio>
              <el-radio :label="1">停用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="cancelForm">确 定</el-button>
      <el-button @click="handleClose">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { addMenu } from "@/api/system/menu";
import IconSelect from "@/components/IconSelect";

export default {
  name: 'addMenu',
  components: { Treeselect , IconSelect },
  props: {
    visible: {
      type: Boolean,
      required: true
    },
    menuList: {
      type: Array,
      default: ()=> []
    }
  },
  data(){
    return {
      form: {
        parentId: undefined,
        name: undefined,
        icon: undefined,
        type: "M",
        path: undefined,
        sort: undefined,
        component: undefined,
        isCache: 0,
        hide: 0,
        status: 0
      },
      rules: {
        parentId: [
          { required: true, message: "上级菜单不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "菜单名称不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "菜单顺序不能为空", trigger: "blur" }
        ],
      }
    }
  },
  computed:{
    menuOptions(){
      let menuOptions = [];
      const menu = { id: 0, name: '主类目', children: [] };
      menu.children = this.handleTree(this.menuList, "id");
      menuOptions.push(menu);
      return menuOptions
    }
  },
  methods: {
    /**
     * 图标
     * @param name
     */
    selected(name) {
      this.form.icon = name;
    },

    /** 转换菜单数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },

    handleClose(){
      this.form={
        parentId: undefined,
        name: undefined,
        icon: undefined,
        type: "M",
        path: undefined,
        sort: undefined,
        component: undefined,
        isCache: 0,
        visible: 0,
        status: 0
      }
      this.$emit('close-drawer');
    },

    /**
     * 确定
     */
    cancelForm(){
      this.$refs["form"].validate(valid => {
        if (valid) {
          addMenu(this.form).then(response => {
            this.$modal.success("新增成功");
            this.$emit("ok");
          });
        }
      });
    }
  }
}
</script>

<style scoped>

</style>
