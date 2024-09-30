<?php
// code for comment
if ( ! function_exists( 'quality_comment' ) ) :
function quality_comment( $comment, $args, $depth ) 
{
	//get theme data
	global $comment_data;

	//translations
	$leave_reply = isset($comment_data['translation_reply_to_coment']) ? $comment_data['translation_reply_to_coment'] : 
	esc_html__('Reply','quality');?>
	
          <div class="media comment-box">
			<a class="pull-left-comment">
            <?php echo get_avatar($comment); ?>
            </a>
          <div class="media-body">
			   <div class="comment-detail">
				<h5 class="comment-detail-title"><?php printf(('%s'), get_comment_author_link()) ?></h5>
				<time class="comment-date"><?php comment_date('F j, Y');?>&nbsp;<?php esc_html_e('at','quality');?>&nbsp;<?php comment_time('g:i a'); ?></time>
				<p><?php comment_text() ;?></p>
				
				<div class="reply">
				
				<?php comment_reply_link(array_merge( $args, array('reply_text' => $leave_reply,'depth' => $depth, 'max_depth' => $args['max_depth']))) ?>
				
				</div>
				<?php if ( $comment->comment_approved == '0' ) : ?>
				<em class="comment-awaiting-moderation"><?php esc_html_e( 'Your comment is awaiting moderation.', 'quality' ); ?></em>
				<br/>
				<?php endif; ?>
				
				</div>
			</div>
		  </div>
<?php
}
endif;
add_filter('get_avatar','quality_add_gravatar_class');
function quality_add_gravatar_class($class) {
    $class = str_replace("class='avatar", "class='comment_img", $class);
    return $class;
}