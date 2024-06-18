<template>
  <div style="border: 1px solid #ccc;">
    <Toolbar
        style="border-bottom: 1px solid #ccc"
        :editor="editor"
        :defaultConfig="toolbarConfig"
        :mode="mode"
        ref="dd"
    />
    <Editor
        :style="{height: height+'px'}"
        v-model="html"
        :defaultConfig="editorConfig"
        :mode="mode"
        @onCreated="onCreated"
        @onChange="onChange"
    />
  </div>
</template>
<script>
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import "@wangeditor/editor/dist/css/style.css"
import {upload} from "@/api/common";

export default {
  name: 'CusEdite',
  components: {Editor,Toolbar},
  props: {
    value: {
      type: String,
      default: undefined
    },
    /* 高度 */
    height: {
      type: Number,
      default: 350,
    },
  },
  data() {
    return {
      filePath: process.env.VUE_APP_BASE_API,  //系统访问地址
      editor: null,   //富文本框对象
      html: "",       //值
      toolbarConfig: {
        toolbarKeys:[
            "headerSelect",  //标题
            "bold",     //粗体
            "italic",   //斜体
            "blockquote", //引用
            "insertLink" , //链接
            {
              key: "group-image",
              title: "图片",
              iconSvg: "<svg viewBox='0 0 1024 1024'><path d=\"M959.877 128l0.123 0.123v767.775l-0.123 0.122H64.102l-0.122-0.122V128.123l0.122-0.123h895.775zM960 64H64C28.795 64 0 92.795 0 128v768c0 35.205 28.795 64 64 64h896c35.205 0 64-28.795 64-64V128c0-35.205-28.795-64-64-64zM832 288.01c0 53.023-42.988 96.01-96.01 96.01s-96.01-42.987-96.01-96.01S682.967 192 735.99 192 832 234.988 832 288.01zM896 832H128V704l224.01-384 256 320h64l224.01-192z\"></path></svg>",
              menuKeys: ["insertImage", "uploadImage"]
            },             //图片
            {
               key: "group-video",
               title: "视频",
               iconSvg: "<svg viewBox='0 0 1024 1024'><path d=\"M981.184 160.096C837.568 139.456 678.848 128 512 128S186.432 139.456 42.816 160.096C15.296 267.808 0 386.848 0 512s15.264 244.16 42.816 351.904C186.464 884.544 345.152 896 512 896s325.568-11.456 469.184-32.096C1008.704 756.192 1024 637.152 1024 512s-15.264-244.16-42.816-351.904zM384 704V320l320 192-320 192z\"></path></svg>",
               menuKeys: ["insertVideo", "uploadVideo"]
            },            //视频
            "codeBlock",  //代码块
            "divider",    //分割线
             "bulletedList",//无序列表
            "numberedList", //有序列表
            "insertTable",//表格
            {
               key: "group-justify",
               title: "对齐",
               iconSvg: "<svg viewBox='0 0 1024 1024'><path d=\"M768 793.6v102.4H51.2v-102.4h716.8z m204.8-230.4v102.4H51.2v-102.4h921.6z m-204.8-230.4v102.4H51.2v-102.4h716.8zM972.8 102.4v102.4H51.2V102.4h921.6z\"></path></svg>",
               menuKeys: ["justifyLeft", "justifyRight", "justifyCenter", "justifyJustify"]
            },        //对齐
            "through",//删除线
            "fontSize", //字号
            "fontFamily", //字体
            "lineHeight"  //行高
        ],
      },
      editorConfig: {
        placeholder: '请输入内容...',   //提示
        MENU_CONF: {
          uploadImage: {
            customUpload: this.uploadImg ,// 自定义图片上传方法
          },
          uploadVideo: {
            customUpload: this.uploadVideo // 自定义视频上传方法
          },
          fontSize: {
            value:'H',
            fontSizeList: [
              '12px',
              '16px',
            ]
          }
        }
      },
      mode: 'default', // or 'simple'      //模式
    }
  },
  watch: {
    value: {
      handler(val) {
        if (val !== this.html) {
          this.html = val === null ? "" : val;
        }
      },
      immediate: true,
    },
  },
  methods: {
    onCreated(editor) {
      this.editor = Object.seal(editor) // 一定要用 Object.seal() ，否则会报错
    },
    // 上传图片
    uploadImg(file, insertFn) {
      upload(file).then((res)=>{
        insertFn(this.filePath+"/"+res.data.fileUrl, res.data.fileName,this.filePath+"/"+res.data.fileUrl)
      }).catch((error)=>{
        this.$modal.error("上传失败")
      })
    },
    // 上传视频
    uploadVideo(file, insertFn) {
      upload(file).then((res)=>{
        insertFn(this.filePath+"/"+res.data.fileUrl)
      }).catch((error)=>{
        this.$modal.error("上传失败")
      })
    },
    onChange() {
      this.$emit("input", this.html)
    },
  },
  beforeDestroy() {
    const editor = this.editor
    if (editor == null) return
    editor.destroy() // 组件销毁时，及时销毁编辑器
  }
}
</script>
<style scoped>
</style>