import { Carousel } from 'react-bootstrap';
import Images from '~/assets/Images';
import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowRight, faArrowLeft } from '@fortawesome/free-solid-svg-icons';

import styles from './Slider.scss';
import classNames from 'classnames';

const cx = classNames.bind(styles);
const data = [
    { img: Images.temp, title: '1th slide' },
    { img: Images.temp, title: '2th slide' },
    { img: Images.temp, title: '3th slide' },
    { img: Images.temp, title: '4th slide' },
];
const slides = data.length;
function Slider() {
    const [index, setIndex] = useState(0);
    const handleSelect = (selectedIndex, e) => {
        setIndex(selectedIndex);
    };
    const handleNext = () => {
        setIndex((index + 1) % slides);
    };
    const handlePrev = () => {
        setIndex(index - 1 >= 0 ? index - 1 : (slides - 1) % slides);
    };
    const toSlide = (n) => {
        setIndex(n);
    };
    return (
        <div>
            <Carousel activeIndex={index} onSelect={handleSelect} controls={false} interval={null} indicators={false}>
                {data.map((item, ind) => {
                    return (
                        <Carousel.Item key={ind}>
                            <img className="d-block w-100" src={item.img} alt={item.title} />
                        </Carousel.Item>
                    );
                })}
            </Carousel>
            <div className={cx('handleControl_carousel')}>
                <div className={cx('handleControl_carousel_to')}>
                    {data.map((item, ind) => {
                        let temp =
                            index === ind ? cx('carousel_slide', 'carousel_slide--active') : cx('carousel_slide');
                        return <div key={ind} onClick={() => toSlide(ind)} className={temp}></div>;
                    })}
                </div>
                <div className={cx('handleControl_carousel_next_prev')}>
                    <div className={cx('carousel_prev')} onClick={handlePrev}>
                        <FontAwesomeIcon icon={faArrowLeft} />
                    </div>
                    <div className={cx('carousel_next')} onClick={handleNext}>
                        <FontAwesomeIcon icon={faArrowRight} />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Slider;
