<template>
  <div>
    <transition name="router-fade" mode="out-in">
      <router-view :key="this.$route.path"/>
    </transition>
    <van-tabbar route>
      <van-tabbar-item to="/home" icon="home-o">主页</van-tabbar-item>
      <van-tabbar-item to="/order" icon="orders-o">订单</van-tabbar-item>
      <van-tabbar-item to="/profile" icon="user-o">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
export default {
  data() {
    return {

    };
  },
}

</script>

<style lang="scss" scoped>
@import '@/assets/style/mixin';

#foot_guide {
  background-color: #fff;
  position: fixed;
  z-index: 100;
  left: 0;
  right: 0;
  bottom: 0;
  @include wh(100%, 1.95rem);
  display: flex;
  box-shadow: 0 -0.026667rem 0.053333rem rgba(0, 0, 0, .1);
}

.guide_item {
  flex: 1;
  display: flex;
  text-align: center;
  flex-direction: column;
  align-items: center;

  .icon_style {
    @include wh(.8rem, .8rem);
    margin-top: .3rem;
    fill: #ccc;
  }

  span {
    @include sc(.45rem, #666);
  }
}

</style>
