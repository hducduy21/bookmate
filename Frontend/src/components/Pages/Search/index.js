import classNames from 'classnames/bind';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Images from '~/assets/Images';
import BookCard from '~/components/cards/BookCard';
import AuthorCard from '~/components/cards/AuthorCard';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';
import { useSelector } from 'react-redux';

import style from './Search.scss';
const cx = classNames.bind(style);
const data = {
    book: [
        { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
        { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
        { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
        { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
        { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
        { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
        { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
    ],
    author: [
        { img: Images.authortemp, name: 'Nguyễn Nhật Ánh', type: 'Tiểu thuyết văn học lứ tuổi thanh thiếu niên' },
        { img: Images.authortemp, name: 'Nguyễn Nhật Ánh', type: 'Tiểu thuyết văn học lứ tuổi thanh thiếu niên' },
        { img: Images.authortemp, name: 'Nguyễn Nhật Ánh', type: 'Tiểu thuyết văn học lứ tuổi thanh thiếu niên' },
        { img: Images.authortemp, name: 'Nguyễn Nhật Ánh', type: 'Tiểu thuyết văn học lứ tuổi thanh thiếu niên' },
        { img: Images.authortemp, name: 'Nguyễn Nhật Ánh', type: 'Tiểu thuyết văn học lứ tuổi thanh thiếu niên' },
        { img: Images.authortemp, name: 'Nguyễn Nhật Ánh', type: 'Tiểu thuyết văn học lứ tuổi thanh thiếu niên' },
        { img: Images.authortemp, name: 'Nguyễn Nhật Ánh', type: 'Tiểu thuyết văn học lứ tuổi thanh thiếu niên' },
        { img: Images.authortemp, name: 'Nguyễn Nhật Ánh', type: 'Tiểu thuyết văn học lứ tuổi thanh thiếu niên' },
    ],
};
const detail = {
    sortby: ['Bán chạy', 'Giá tăng dần', 'Giá giảm dần'],
    pricerange: ['Dưới 100.000đ', 'từ 100.000đ đến 300.000đ', 'từ 300.000đ đến 600.000đ', 'Trên 600.000đ'],
    type: ['Bán chạy', 'Giá tăng dần', 'Giá giảm dần'],
};
function Search() {
    const context = useSelector((state)=>state.Language)
    let [search_list, setList] = useState({ sortby: 1, priceRange: 0, type: 0 });
    const setSortBy = (n) => {
        setList((pre) => {
            return { ...pre, sortby: n };
        });
    };
    const setPriceRange = (n) => {
        setList((pre) => {
            return { ...pre, priceRange: n };
        });
    };
    const setType = (n) => {
        setList((pre) => {
            return { ...pre, type: n };
        });
    };
    return (
        <Row className={cx('search_wrapper')}>
            <Col xl={3} className={cx('search_wrapper_detail')}>
                <div className={cx('search_detail')}>
                    <div className={cx('search_detail_sortby')}>
                        <span>Sắp xếp theo:</span>
                        <select name="search_sortby" id="search_sortby">
                            <option value="0"></option>
                            <option value="1">Bán chạy</option>
                            <option value="2">Giá tăng dần</option>
                            <option value="3">Giá giảm dần</option>
                        </select>
                    </div>
                    <div className={cx('search_detail_pricerange')}>
                        <span>Giá từ:</span>
                        <select name="search_pricerange" id="search_pricerange">
                            <option value="0"></option>
                            <option value="1">Dưới 100.000đ</option>
                            <option value="2">từ 100.000đ đến 300.000đ</option>
                            <option value="3">từ 300.000đ đến 600.000đ</option>
                            <option value="3">Trên 600.000đ</option>
                        </select>
                    </div>
                    <div className={cx('search_detail_type')}>
                        <span>Thể loại:</span>
                        <select name="search_type" id="search_type">
                            <option value="0"></option>
                            <option value="1">Văn học</option>
                            <option value="2">Truyện ngắn</option>
                            <option value="3">Truyện tranh</option>
                            <option value="4">Văn phòng phẩm</option>
                            <option value="5">Sách giáo khoa</option>
                        </select>
                    </div>
                </div>
                <div className={cx('search_infor')}>
                    <div className={cx('search_infor_detail')}></div>
                </div>
            </Col>
            <Col xl={9} className={cx('search_wrapper_result')}>
                <div className={cx('search_result')}>
                    <h3 className={cx('search_title')}>{context.bookResultTitle}</h3>
                    {data.book.map((e, i) => {
                        return (
                            <BookCard
                                key={i}
                                name={e.name}
                                author={e.author}
                                price={e.price}
                                img={e.img}
                                width="true"
                            />
                        );
                    })}
                    <h3 className={cx('search_title')}>{context.authorResultTitle}</h3>
                    {data.author.map((e, i) => {
                        return (
                            <AuthorCard
                                key={i}
                                name={e.name}
                                type={e.type}
                                img={e.img}
                                width="true"
                            />
                        );
                    })}
                </div>
            </Col>
        </Row>
    );
}

export default Search;
