import Vue from 'vue'
import SvgIcon from '@/components/SvgIcon'

// register globally
Vue.component('svg-icon', SvgIcon)
const req = require.context('./svg', false, /\.svg$/)
const requireAll = requireContext => requireContext.keys().map(requireContext)
requireAll(req)
const iconNames = req.keys().map(key => {
    const iconName = key.replace(/(\.\/|\.svg)/g, '');
    return iconName;
});
export default iconNames;
