<script setup lang="ts">
import { ref, computed, watch } from 'vue'
import { useUsers } from '@/stores/useUsers'
import type User from '@/types/User'
import { useRouter } from 'vue-router'

const router = useRouter()

const userStore = useUsers()

const props = defineProps<{
  user: User
}>()

const user = ref<User>({
  id: props.user.id,
  username: props.user.username,
  name: props.user.name,
  roles: props.user.roles
})

const updateUserInvalid = computed<boolean>(
  () => user.value.username === '' || user.value.name === ''
)

const updateUser = () => {
  if (!updateUserInvalid.value) {
    userStore.adminUpdateUser(user.value)
  }
}

watch(
  () => userStore.users,
  () => {
    if (!userStore.error) {
      router.push({
        name: 'admin users'
      })
    }
  }
)
</script>

<template>
  <div class="container">
    <div class="card bg-light my-2">
      <div class="card-header">{{ props.user.name }} - {{ props.user.username }}</div>
      <div class="card-body">
        <form class="form">
          <div class="row mb-3">
            <label for="username" class="col-sm-2 col-form-label">Username</label>
            <div class="col-sm-10">
              <input name="username" id="username" class="form-control" v-model="user.username" />
            </div>
          </div>
          <div class="row mb-3">
            <label for="name" class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-10">
              <input name="name" id="name" class="form-control" v-model="user.name" />
            </div>
          </div>
          <button
            type="button"
            class="btn btn-sm btn-primary"
            :disabled="updateUserInvalid"
            @click="updateUser"
          >
            Update User
          </button>
        </form>
      </div>
    </div>
  </div>
</template>
