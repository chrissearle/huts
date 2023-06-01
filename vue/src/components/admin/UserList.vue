<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { useUsers } from '@/stores/useUsers'
import type User from '@/types/User'

const userStore = useUsers()

const updatePassword = ref('')

const updatePasswordInvalid = computed<boolean>(() => updatePassword.value === '')

const newPassword = (user: User) => {
  if (!updatePasswordInvalid.value) {
    userStore.adminChangePassword(user, updatePassword.value)
  }
}

onMounted(() => {
  userStore.retrieve(true)
})
</script>

<template>
  <div class="container">
    <div class="card bg-light my-2">
      <div class="card-header">Users</div>
      <div class="card-body">
        <table class="table table-striped">
          <thead>
            <tr>
              <th>Username</th>
              <th>Name</th>
              <th class="text-center">Admin</th>
              <th class="text-center">Change:</th>
              <th class="text-center">Delete</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(user, index) in userStore.sortedUsers" :key="index">
              <td>
                <router-link :to="`/admin/user/${user.id}`">
                  {{ user.username }}
                </router-link>
              </td>
              <td>{{ user.name || '' }}</td>
              <td class="text-center">
                <button
                  type="button"
                  v-if="user.roles.includes('ADMIN')"
                  class="btn btn-sm btn-warning"
                  @click="userStore.adminUpdateRoles(user, ['USER'])"
                >
                  Remove Admin
                </button>
                <button
                  type="button"
                  v-else
                  class="btn btn-sm btn-secondary"
                  @click="userStore.adminUpdateRoles(user, ['USER', 'ADMIN'])"
                >
                  Make Admin
                </button>
              </td>

              <td class="text-center">
                <button
                  type="button"
                  class="ms-2 btn btn-sm btn-secondary"
                  :disabled="updatePasswordInvalid"
                  @click="newPassword(user)"
                >
                  Password
                </button>
              </td>

              <td class="text-center">
                <button
                  type="button"
                  class="ms-2 btn btn-sm btn-danger"
                  @click="userStore.adminDeleteUser(user)"
                >
                  Delete
                </button>
              </td>
            </tr>
          </tbody>
        </table>
        <div class="card-footer">
          <form class="form">
            <div class="row mb-3">
              <label for="updatePassword" class="col-sm-2 col-form-label">Password</label>
              <div class="col-sm-10">
                <input
                  name="updatePassword"
                  id="updatePassword"
                  class="form-control"
                  v-model="updatePassword"
                />
              </div>
              <div class="form-text offset-sm-2">Password used for "Change Password"</div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
