import Layout from "@/layout"
export const branchRouter = [
    {
        path: '',
        component: Layout,
        hidden: true,
        redirect: 'noredirect',
        children: [
            {
                path: 'fileDetail',
                component: () => import('@/views/file/detail/index.vue'),
                name: 'fileDetail',
                meta: { title: '文件详情', icon: 'file' }
            }
        ]
    },
    {
        path: '',
        component: Layout,
        hidden: true,
        redirect: 'noredirect',
        children: [
            {
                path: 'operBug',
                component: () => import('@/views/study/bugRecords/components/operBug.vue'),
                name: 'operBug',
                meta: { title: '操作记录', icon: 'file' }
            }
        ]
    },
    {
        path: '',
        component: Layout,
        hidden: true,
        redirect: 'noredirect',
        children: [
            {
                path: 'bugInfo',
                component: () => import('@/views/study/bugRecords/components/bugInfo.vue'),
                name: 'bugInfo',
                meta: { title: '错误记录详情', icon: 'file' }
            }
        ]
    },
    {
        path: '',
        component: Layout,
        hidden: true,
        redirect: 'noredirect',
        children: [
            {
                path: 'dictData',
                component: () => import('@/views/system/dict/dictData.vue'),
                name: 'dictData',
                meta: { title: '字典数据', icon: 'file' }
            }
        ]
    }
];

export default branchRouter
