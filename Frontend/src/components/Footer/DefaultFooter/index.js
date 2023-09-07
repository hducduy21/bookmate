import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFacebook, faInstagram, faYoutube, faLinkedin } from '@fortawesome/free-brands-svg-icons';
import { faLocationDot } from '@fortawesome/free-solid-svg-icons';
import styles from './Footer.scss';
import classNames from 'classnames';
const cx = classNames.bind(styles);

function DefaultFooter() {
    return (
        <footer className={cx('site-footer')}>
            <div className="container">
                <div className="row">
                    <div className="col-sm-12 col-md-6">
                        <h6>Về chúng tôi</h6>
                        <p className="text-justify"></p>
                    </div>

                    <div className="col-xs-6 col-md-3">
                        <h6>Hoạt động</h6>
                        <ul className="footer-links">
                            <li>
                                <a href="#">Liên hệ hợp tác</a>
                            </li>
                            <li>
                                <a href="#">Chính sách bảo mật</a>
                            </li>
                            <li>
                                <a href="#">Điều khoản sử dụng</a>
                            </li>
                            <li>
                                <a href="#">Khiếu nại</a>
                            </li>
                        </ul>
                    </div>

                    <div className="col-xs-6 col-md-3">
                        <h6>Địa chỉ trụ sở</h6>
                        <ul className="footer-links">
                            <li style={{ margin: '0 0 20px 0' }}>
                                <FontAwesomeIcon icon={faLocationDot} />
                                <a href="#">19 Nguyễn Hữu Thọ, phường Tân Phong, Quận 7, Tp HCM</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <hr />
            </div>
            <div className="container">
                <div className="row">
                    <div className="col-md-8 col-sm-6 col-xs-12">
                        <p className="copyright-text">
                            Copyright &copy; 2017 All Rights Reserved by
                            <a href="#"> Hoang Duc Duy</a>.
                        </p>
                    </div>

                    <div className="col-md-4 col-sm-6 col-xs-12">
                        <ul className="social-icons">
                            <FontAwesomeIcon icon={faFacebook} className="facebook" />
                            <FontAwesomeIcon icon={faYoutube} className="twitter" />
                            <FontAwesomeIcon icon={faInstagram} className="dribbble" />
                            <FontAwesomeIcon icon={faLinkedin} className="linkedin" />
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
    );
}

export default DefaultFooter;
