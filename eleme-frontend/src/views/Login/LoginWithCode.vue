<template>
  <div class="login-form">
    <div class="top">
      <van-icon name="cross"/>
      <router-link to="/login/pwd" class="pwd">密码登录</router-link>
    </div>
    <div class="title">邮箱登录</div>
    <van-field
        v-model="loginForm.email"
        placeholder="请输入邮箱"
    />
    <van-field
        v-model="loginForm.code"
        placeholder="验证码"
    >
      <template #button>
        <van-button
            size="small"
            type="info"
            round plain
            @click="sendCode"
        >发送验证码
        </van-button>
      </template>
    </van-field>
    <div style="margin: 16px;">
      <van-button round block type="info" @click="onSubmit">提交</van-button>
    </div>
  </div>
</template>

<script>
import {Login, sendCode} from "@/api/login";
import {Toast} from "vant";

export default {
  data() {
    return {
      loginForm: {
        email: '',
        code: '',
        uuid: '',
        method: 'USERNAME_CODE'
      }
    };
  },
  methods: {
    onSubmit() {
      Login(this.loginForm).then(res => {
        Toast.success(res.data.msg)
      })
    },
    sendCode() {
      sendCode(this.loginForm.email).then(res => {
        this.loginForm.uuid = res.data.uuid
        Toast.success(res.data.msg)
      })
    }
  },
};
</script>

<style scoped lang="scss">
.login-form {
  padding: 10px 10px;

  .title {
    font-size: 30px;
    padding-left: 16px;
    padding-bottom: 30px;
  }

  .van-cell.van-field {
    font-size: 20px;
  }

  .router-link-active {
    color: black;
  }
}
</style>
