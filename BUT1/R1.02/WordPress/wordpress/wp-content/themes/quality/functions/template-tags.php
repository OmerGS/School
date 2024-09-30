<?php

// blogs,pages and archive page title
function quality_archive_page_title() {
    if (is_archive()) {
        $archive_text = get_theme_mod('archive_prefix', esc_html__('Archives: ', 'quality'));

        echo '<h1>';

        if (is_day()) :

            printf( esc_html__('%1$s %2$s', 'quality'), esc_html( $archive_text ), esc_html( get_the_date() ));

        elseif (is_month()) :

            printf(esc_html__('%1$s %2$s', 'quality'), esc_html( $archive_text ), esc_html( get_the_date()));

        elseif (is_year()) :

            printf(esc_html__('%1$s %2$s', 'quality'), esc_html( $archive_text ), esc_html( get_the_date() ));

        elseif (is_category()):

            $category_text = get_theme_mod('category_prefix', esc_html__('Category', 'quality'));

            printf(esc_html__('%1$s %2$s', 'quality'), esc_html($category_text), esc_html( single_cat_title('', false)));

        elseif (is_author()):

            $author_text = get_theme_mod('author_prefix', esc_html__('All posts by', 'quality'));

            printf(esc_html__('%1$s %2$s', 'quality'), esc_html($author_text), esc_html( get_the_author()));

        elseif (is_tag()):

            $tag_text = get_theme_mod('tag_prefix', esc_html__('Tag', 'quality'));

            printf(esc_html__('%1$s %2$s', 'quality'), esc_html($tag_text), esc_html( single_tag_title('', false)));

        elseif (is_shop()):

            $shop_text = get_theme_mod('shop_prefix', esc_html__('Shop', 'quality'));

            printf(esc_html__('%1$s %2$s', 'quality'), esc_html($shop_text), esc_html( single_tag_title('', false)));

        elseif (is_archive()):
            the_archive_title('<h1>', '</h1>');

        endif;


        echo '</h1>';
    } elseif (is_search()) {
        $search_text = get_theme_mod('search_prefix', esc_html__('Search results for', 'quality'));

        echo '<h1>';

        printf(esc_html__('%1$s %2$s', 'quality'), esc_html($search_text), get_search_query());

        echo '</h1>';
    } elseif (is_404()) {
        $breadcrumbs_text = get_theme_mod('404_prefix', esc_html__('404', 'quality'));

        echo '<h1>';

        printf(esc_html__('Error %1$s', 'quality'), esc_html($breadcrumbs_text));

        echo '</h1>';
    } else {
        echo '<h1>' . esc_html( get_the_title() ) . '</h1>';
    }
}

// Quality post navigation
function quality_post_nav() {
    global $post;
    echo '<div style="text-align:center;">';
    posts_nav_link(' &#183; ', esc_html__('previous page', 'quality'), esc_html__('next page', 'quality'));
    echo '</div>';
}

//Hide Title of woocommerce shop page
add_filter('woocommerce_show_page_title', 'quality_woo_hide_page_title');

function quality_woo_hide_page_title() {

    return false;
}

add_action('wp_footer', 'quality_demo_store');

function quality_demo_store() {
    if (class_exists('WooCommerce')) {
        $woocommerce_demo_store = get_option('woocommerce_demo_store', 'no');
        if ($woocommerce_demo_store != 'no') {
            ?>
            <style>
                .woocommerce-store-notice .demo_store, #wrapper {
                    margin-top: 50px !important;
                }
            </style>
        <?php

        }
    }
}
