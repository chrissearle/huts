import { createRouter, createWebHashHistory } from 'vue-router'

import CalendarView from '@/views/CalendarView.vue'

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      name: 'cal',
      component: CalendarView
    }
  ]
})

export default router
