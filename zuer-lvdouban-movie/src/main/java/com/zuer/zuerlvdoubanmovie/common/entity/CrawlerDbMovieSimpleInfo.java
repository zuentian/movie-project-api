package com.zuer.zuerlvdoubanmovie.common.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class CrawlerDbMovieSimpleInfo {
    private String cover;
    private String id;
    private String rate;
    private String playable;
    private String title;
    private String url;
    private String is_new;
    private String cover_y;
    private String cover_x;
    private String base64Photo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrawlerDbMovieSimpleInfo info = (CrawlerDbMovieSimpleInfo) o;
        return Objects.equals(cover, info.cover) &&
                Objects.equals(id, info.id) &&
                Objects.equals(rate, info.rate) &&
                Objects.equals(playable, info.playable) &&
                Objects.equals(title, info.title) &&
                Objects.equals(url, info.url) &&
                Objects.equals(is_new, info.is_new) &&
                Objects.equals(cover_y, info.cover_y) &&
                Objects.equals(cover_x, info.cover_x) &&
                Objects.equals(base64Photo, info.base64Photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cover, id, rate, playable, title, url, is_new, cover_y, cover_x, base64Photo);
    }
}
