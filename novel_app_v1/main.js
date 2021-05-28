import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

App.mpType = 'app'

// import Vuex from 'vuex'
// Vue.use(Vuex)

import store from './store'

// 导入 uview
import uView from 'uview-ui';
Vue.use(uView);
Vue.baseUrl = 'http://novel_backend.lyr-2000.xyz/'


const app = new Vue({
    ...App,
	store
})
app.$mount()
