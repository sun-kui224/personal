<template>
  <svg aria-hidden="true" :class="svgClass" v-bind="$attrs"
       :style="{ color, fill: color, width: iconSize, height: iconSize }">
    <use :xlink:href="iconName"></use>
  </svg>
</template>

<script>

export default {
  name: 'SvgIcon',
  props: {
    svgName: {
      type: String,
      required: true
    },
    color: {
      type: String,
      default: ''
    },
    size: {
      type: Number,
      default: 20
    }
  },
  computed: {
    iconSize() {
      return /(px|em|rem|%)$/.test(this.size.toString()) ? this.size : `${this.size}px`
    },
    iconName() {
      return `#icon-${this.svgName}`
    },
    svgClass() {
      if (this.svgName) {
        return `svg-icon icon-${this.svgName}`
      } else {
        return 'svg-icon'
      }
    },
  },
}
</script>

<style scoped>
.svg-icon {
  width: auto;
  height: auto;
  vertical-align: middle;
  flex-shrink: 0;
}
</style>
