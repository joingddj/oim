import platformInitialize from '@/platform/initialize/PlatformInitialize';

platformInitialize.initialize();

import Vue from 'vue';
import App from './App.vue';
import './registerServiceWorker';
import routerManager from './router/RouterManager';
import store from './store';
import vuetify from './plugins/vuetify';
import './plugins/codemirror';

import van from 'vant';
import 'vant/lib/index.css';

Vue.use(van);

import 'viewerjs/dist/viewer.css';
// @ts-ignore
import Viewer from 'v-viewer';

Vue.use(Viewer);

Vue.config.productionTip = false;

const router = routerManager.getRouter();
new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App)
}).$mount('#app');
