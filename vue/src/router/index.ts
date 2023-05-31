import { createRouter, createWebHashHistory } from 'vue-router'

import CalendarView from '@/views/CalendarView.vue'
import LoginForm from '@/views/LoginForm.vue'

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      name: 'cal',
      component: CalendarView
    },
    {
      path: '/login',
      name: 'login',
      props: true,
      component: LoginForm
    }
  ],
  scrollBehavior(_to, _from, _savedPosition) {
    return { top: 0 }
  }
})

export default router
