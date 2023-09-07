import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Images from '~/assets/Images';
import Search from '~/components/Search';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCartShopping } from '@fortawesome/free-solid-svg-icons';

import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import styles from './DefaultHeader.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

function DefaultHeader() {
    const dispatch = useDispatch();
    const context = useSelector((state) => state.Language);
    const language = useSelector((state) => state.Language.lan);
    const [lan, setLan] = useState(language);
    return (
        <Navbar expand="lg" className={cx('wrapper_header')} fixed="top">
            <Container className={cx('container')}>
                <Navbar.Brand href="/home" className={cx('brand')}>
                    <img src={Images.logo} alt="img" className={cx('logo')}></img>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Search />
                    </Nav>
                    <Nav>
                        <NavDropdown title={context.type} id="dropdown-menu-align-end">
                            <NavDropdown.Item href="#action/3.1">Văn học</NavDropdown.Item>
                            <NavDropdown.Item href="#action/3.2">Tiểu thuyết</NavDropdown.Item>
                            <NavDropdown.Item href="#action/3.3">Truyện ngắn</NavDropdown.Item>
                        </NavDropdown>
                        <NavDropdown title={context.cart} id="dropdown-menu-align-end">
                            <NavDropdown.Item href="#action/3.1">Văn học</NavDropdown.Item>
                            <NavDropdown.Item href="#action/3.2">Tiểu thuyết</NavDropdown.Item>
                            <NavDropdown.Item href="#action/3.3">Truyện ngắn</NavDropdown.Item>
                        </NavDropdown>
                        <Nav.Link href="/cart">
                            <FontAwesomeIcon icon={faCartShopping} /> {context.cart}
                        </Nav.Link>
                    </Nav>

                    <Nav>
                        <Nav.Link href="/login" className={cx('login')}>
                            <div className={cx('btn_login')}>{context.login}</div>
                        </Nav.Link>
                        <NavDropdown title={lan} id="dropdown-menu-align-end">
                            <NavDropdown.Item
                                onClick={() => {
                                    dispatch({ type: 'TO_VI' });
                                    setLan('Vi');
                                }}
                            >
                                Vi
                            </NavDropdown.Item>
                            <NavDropdown.Item
                                onClick={() => {
                                    dispatch({ type: 'TO_EN' });
                                    setLan('En');
                                }}
                            >
                                En
                            </NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default DefaultHeader;
