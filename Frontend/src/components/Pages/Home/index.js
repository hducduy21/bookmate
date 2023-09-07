import Slider from '~/components/Slider';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Slick from '~/components/Slick';
import { useSelector } from 'react-redux';

import styles from './Home.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

function Home() {
    const context = useSelector((state) => state.Language);
    return (
        <div className={cx('Home_wrapper')}>
            <Row className={cx('Slider_wrapper')}>
                <Col xl={4} lg={4} className={cx('Slider_title')}>
                    <div className={cx('Slider_title_main')}>{context.slogan}</div>
                    <div className={cx('Slider_title_to')}>
                        <span>{context.let}</span>
                    </div>
                </Col>
                <Col xl={8} lg={8}>
                    <Slider className={cx('Slider_content')}></Slider>
                </Col>
            </Row>
            <div className={cx('Book_slick_wrapper')}>
                <div className={cx('Book_slick_title')}>
                    <h3>Sách nổi bật</h3>
                </div>
                <Slick type={1} />
            </div>
            <div className={cx('Book_slick_wrapper')}>
                <div className={cx('Book_slick_title')}>
                    <h3>Sách nổi bật</h3>
                </div>
                <Slick type={2} />
            </div>
        </div>
    );
}

export default Home;
