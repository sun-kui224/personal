<template>
  <div id ="app">
    <i-frame :src="url"/>
  </div>
</template>

<script>
import iFrame from "@/components/iFrame/index";
export default {
  name: 'interDebugger',
  components: { iFrame },
  data() {
    return {
      url: process.env.VUE_APP_BASE_API + "/doc.html",
    }
  },
  mounted() {
  },
  methods: {

  }
}
</script>
<style scoped>

</style>
