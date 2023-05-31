<script setup lang="ts">
import type LoginRequest from '@/types/Login'
import { computed, ref, watch } from 'vue'
import { useAuth } from '@/stores/useAuth'
import { useRouter } from 'vue-router'

const router = useRouter()

const user = ref({
  username: '',
  password: ''
} as LoginRequest)

const authStore = useAuth()

const loginEnabled = computed<boolean>(
  () => user.value.username.length > 3 && user.value.password.length > 3
)

const submit = () => {
  if (loginEnabled.value) {
    authStore.login(user.value)
    user.value.password = ''
  }
}

watch(
  () => authStore.roles,
  async (newRoles) => {
    if (newRoles.length > 0) {
      authStore.$persist()
      router.push({
        name: 'cal'
      })
    }
  }
)

const isLoggedIn = computed<boolean>(() => authStore.roles.length > 0)
</script>

<template>
  <div class="container my-2" v-if="!isLoggedIn">
    <h1>Logg på</h1>

    <div v-if="authStore.error" class="alert alert-dismissible my-2 alert-danger" role="alert">
      <h2>Pålogging feilet</h2>
      <p>Vennligst prøv igjen</p>
    </div>

    <form class="form" @submit.prevent="submit">
      <div class="row mb-3">
        <label for="username" class="col-sm-2 col-form-label">Brukernavn</label>
        <div class="col-sm-10">
          <input name="username" id="username" class="form-control" v-model="user.username" />
        </div>
      </div>
      <div class="row mb-3">
        <label for="password" class="col-sm-2 col-form-label">Passord</label>
        <div class="col-sm-10">
          <input
            name="password"
            id="password"
            type="password"
            class="form-control"
            v-model="user.password"
          />
        </div>
      </div>
      <button type="submit" class="btn btn-primary offset-sm-2" :disabled="!loginEnabled">
        Logg på
      </button>
    </form>
  </div>
</template>

<style scoped>
div.alert {
  margin-top: 20px !important;
  margin-bottom: 20px !important;
}
</style>
