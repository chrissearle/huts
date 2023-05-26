<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
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

        <ul class="navbar-nav mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link to="/login" class="nav-link" active-class="active">Logg på</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <router-view />
</template>
