import DedaultLayout from '~/components/Layouts/DefaultLayout';
import Home from '~/components/Pages/Home';
import Search from '~/components/Pages/Search';
import cart from '~/components/cart';

const routes = [
    { path: '/', element: Home, layout: DedaultLayout },
    { path: '/home', element: Home, layout: DedaultLayout },
    { path: '/search', element: Search, layout: DedaultLayout },
    { path: '/cart', element: cart, layout: DedaultLayout },
];

export { routes };
