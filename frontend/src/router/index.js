import Vue from 'vue';
import VueRouter from 'vue-router';
import HomeView from '../views/Home.vue';
import NumberingView from '../views/Numbering.vue';

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomeView
    },
    {
        path: '/numbering',
        name: 'Numbering',
        component: NumberingView
    }
];

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router;
