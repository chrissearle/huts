import { createRouter, createWebHashHistory } from 'vue-router'

import AdminCreateUser from '@/views/admin/AdminCreateUser.vue'
import AdminEditUser from '@/views/admin/AdminEditUser.vue'
import AdminUsers from '@/views/admin/AdminUsers.vue'
import CalendarView from '@/views/CalendarView.vue'
import ChangePassword from '@/views/ChangePassword.vue'
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
      path: '/password',
      name: 'password',
      props: true,
      component: ChangePassword
    },
    {
      path: '/login',
      name: 'login',
      props: true,
      component: LoginForm
    },

    {
      path: '/admin/users',
      name: 'admin users',
      props: true,
      component: AdminUsers
    },
    {
      path: '/admin/users/new',
      name: 'admin new user',
      props: true,
      component: AdminCreateUser
    },
    {
      path: '/admin/user/:id',
      name: 'admin user',
      props: true,
      component: AdminEditUser
    }
  ],
  scrollBehavior(_to, _from, _savedPosition) {
    return { top: 0 }
  }
})

export default router
