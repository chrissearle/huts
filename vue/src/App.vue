<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'

import { useAuth } from '@/stores/useAuth'
import { useRouter } from 'vue-router'
import { watch } from 'vue'

import StoreAlerts from '@/components/StoreAlerts.vue'

const router = useRouter()
const authStore = useAuth()

const logout = () => {
  authStore.reset()

  router.push({
    name: 'cal'
  })
}

watch(
  () => authStore.roles,
  async (newRoles, oldRoles) => {
    if (newRoles.length === 0 && oldRoles.length > 0) {
      router.push({
        name: 'cal'
      })
    }
  }
)
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark d-print-none mb-3">
    <div class="container">
      <router-link to="/" class="navbar-brand ms-2">Hytter</router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link to="/" class="nav-link" active-class="active">Kalender</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/" class="nav-link" active-class="active">Bestill</router-link>
          </li>
        </ul>

        <ul v-if="authStore.isUser" class="navbar-nav mb-2 mb-lg-0">
          <li v-if="authStore.isAdmin" class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="adminDropdown"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Admin
            </a>
            <ul class="dropdown-menu" aria-labelledby="adminDropdown">
              <li>
                <router-link to="/admin/users" class="dropdown-item">User admin</router-link>
              </li>
            </ul>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="adminDropdown"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <i class="bi bi-person px-2"></i>
              {{ authStore.name }}
            </a>
            <ul class="dropdown-menu" aria-labelledby="userDropdown">
              <li>
                <router-link to="/password" class="dropdown-item" active-class="active">
                  Bytt passord
                </router-link>
              </li>
              <li>
                <button type="button" class="dropdown-item" @click="logout">Logg ut</button>
              </li>
            </ul>
          </li>
        </ul>
        <ul v-else class="navbar-nav mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link to="/login" class="nav-link" active-class="active">Logg på</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <store-alerts />

  <router-view />
</template>
