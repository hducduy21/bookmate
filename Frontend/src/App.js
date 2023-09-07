import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import DefaultLayout from './components/Layouts/DefaultLayout';
import { routes } from './components/routes';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
    return (
        <Router>
            <div className="App">
                <Routes>
                    {routes.map((route, ind) => {
                        const Clayout = route.layout || DefaultLayout;
                        const Page = route.element;
                        return (
                            <Route
                                key={ind}
                                path={route.path}
                                element={
                                    <Clayout>
                                        1
                                        <Page />
                                    </Clayout>
                                }
                            />
                        );
                    })}
                </Routes>
            </div>
        </Router>
    );
}

export default App;
