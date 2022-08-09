<template>
  <div>
    <van-button @click="Login">登录</van-button>
  </div>
</template>

<script>
export default {
  name: "Profile",
  methods: {
    Login() {
      this.$router.push("/login")
    }
  }
}
</script>

<style scoped>

</style>
