import { computed, ref } from 'vue'

import type { CreateUser } from '@/types/User'
import type User from '@/types/User'
import axios, { type Method } from 'axios'
import { defineStore } from 'pinia'
import { useStoreUtils } from '@/stores/useStoreUtils'

const storeUtils = useStoreUtils()

export const useUsers = defineStore(
  'usersStore',
  () => {
    const users = ref<User[]>([])
    const error = ref<string[] | undefined>(undefined)

    const retrieve = async (force: Boolean = false) => {
      error.value = undefined

      if (users.value.length > 0 && !force) {
        return
      }

      try {
        const data = await axios.get<User[]>('/api/admin/user', storeUtils.buildConfig())

        users.value = data.data
      } catch (err) {
        error.value = storeUtils.handleError(err)
      }
    }

    const sortedUsers = computed<User[]>(() => {
      return users.value.sort((a, b) => a.username.localeCompare(b.username))
    })

    const performUpdate = async <T extends {}>(url: string, method: Method, data: T) => {
      error.value = undefined

      try {
        await axios({
          method: method,
          url: url,
          ...(Object.keys(data).length > 0 ? { data: data } : {}),
          ...storeUtils.buildConfig()
        })

        retrieve(true)
      } catch (err) {
        error.value = storeUtils.handleError(err)
      }
    }

    const adminCreateUser = async (user: CreateUser) =>
      performUpdate('/api/admin/user', 'POST', user)

    const adminUpdateUser = async (user: User) =>
      performUpdate(`/api/admin/user/${user.id}`, 'PATCH', {
        username: user.username,
        name: user.name
      })

    const adminDeleteUser = async (user: User) =>
      performUpdate(`/api/admin/user/${user.id}`, 'DELETE', {})

    const adminChangePassword = async (user: User, password: string) =>
      performUpdate(`/api/admin/user/${user.id}/change_password`, 'POST', {
        password: password
      })

    const adminUpdateRoles = async (user: User, roles: Array<string>) =>
      performUpdate(`/api/admin/user/${user.id}/update_roles`, 'POST', { roles: roles })

    const changePassword = async (password: string, oldPassword: string) =>
      performUpdate('/api/user/change_password', 'POST', {
        oldPassword: oldPassword,
        newPassword: password
      })

    const clear = () => (error.value = undefined)

    return {
      users,
      error,
      sortedUsers,
      retrieve,
      clear,
      adminCreateUser,
      adminUpdateUser,
      adminDeleteUser,
      adminChangePassword,
      adminUpdateRoles,
      changePassword
    }
  },
  {
    persist: false
  }
)
