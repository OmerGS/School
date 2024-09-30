<?php
/*	@Theme Name	:	Quality
* 	@file         :	comments.php
* 	@package      :	Quality-Pro
* 	@license      :	license.txt
* 	@filesource   :	wp-content/themes/quality/comments.php
*/
?>
<?php if ( post_password_required() ) : ?>
	<p class="nopassword"><?php esc_html_e( 'This post is password protected. Enter the password to view any comments.', 'quality' ); ?></p>
	<?php return; endif; ?>
         <?php if ( have_comments() ) : ?>
        <div class="comment-section">	
			<div class="comment-title">
				<h3>
				<?php comments_number ( esc_html__('No Comments','quality'), esc_html__( 'One Comment','quality'),'% ' . esc_html__('Comments','quality') ); ?>
                                </h3>
			</div>
			<?php if ( get_comment_pages_count() > 1 && get_option( 'page_comments' ) ) :  ?>		
			<?php endif; ?>
			<?php wp_list_comments( array( 'callback' => 'quality_comment' ) ); ?>
		</div>
		
		<?php if ( get_comment_pages_count() > 1 && get_option( 'page_comments' ) ) : ?>
		<nav id="comment-nav-below">
			<h1 class="assistive-text"><?php esc_html_e( 'Comment navigation', 'quality' ); ?></h1>
			<div class="nav-previous"><?php previous_comments_link( esc_html__( '&larr; Older Comments', 'quality' ) ); ?></div>
			<div class="nav-next"><?php next_comments_link( esc_html__( 'Newer Comments &rarr;', 'quality' ) ); ?></div>
		</nav>
		<?php endif;  ?>
		<?php elseif ( ! comments_open() && ! is_page() && post_type_supports( get_post_type(), 'comments' ) ) : 
        //_e("Comments Are Closed!!!",'quality');
		?>
	<?php endif; ?>
	<?php if ('open' == $post->comment_status) : ?>
	<?php if ( get_option('comment_registration') && isset($user_ID) ) : ?>
		<p><?php echo sprintf( __( 'You must be <a href="%s">logged in</a> to post a comment','quality' ), esc_url(site_url( 'wp-login.php' )) . '?redirect_to=' .  urlencode(esc_url(get_permalink())) ); ?></p>
</p>
<?php else : ?>
	<article class="comment-form-section">
	<?php  
	 $quality_fields=array(
		'author' => '<div class="qua_form_group"><label>'.esc_html__("Name",'quality').'<small>*</small></label><input class="qua_con_input_control" name="author" id="author" value="" type="text"/></div>',
		'email' => '<div class="qua_form_group"><label>'.esc_html__("Email",'quality').'<small>*<small></label><input class="qua_con_input_control" name="email" id="email" value=""   type="email" ></div>',		
		);
	function quality_my_fields($quality_fields) { 
		return $quality_fields;
	}
	add_filter('comment_form_default_fields','quality_my_fields');
		$quality_defaults = array(
		'fields'=> apply_filters( 'comment_form_default_fields', $quality_fields ),
		'comment_field'=> '<div class="qua_form_group"><label>'.esc_html__("Comment",'quality').'</label>
		<textarea id="comments" rows="5" class="qua_con_textarea_control" name="comment" type="text"></textarea></div>',		
		'logged_in_as' => '<p class="logged-in-as">' . esc_html__( "Logged in as",'quality' ).' '.'<a href="'. esc_url(admin_url( 'profile.php' )) .'">'.$user_identity.'</a>'. '  <a href="'. esc_url(wp_logout_url( get_permalink() )).'" title= '. esc_attr__("Log out of this account", 'quality').' >'.esc_html__("Logout",'quality').'</a>' . '</p>',
		'id_submit'=> 'qua_send_button',
		'label_submit'=>esc_html__( 'Post comment','quality'),
		'comment_notes_after'=> '',
		'title_reply'=> '<h2>'.esc_html__('Leave a Reply','quality').'</h2>',
		'id_form'=> 'action'
		);
	comment_form($quality_defaults);?>						
<?php endif; // If registration required and not logged in ?>
<?php endif;