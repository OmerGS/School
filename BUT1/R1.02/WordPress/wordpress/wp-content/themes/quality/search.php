<?php 
get_header();
quality_breadcrumbs(); ?>
<section id="section-block" class="site-content">
	<div class="container">
		<div class="row">	

		<!--Blog Content-->
		<div class="col-md-<?php echo ( !is_active_sidebar( 'sidebar-primary' ) ? '12' :'8' ); ?> col-xs-12">
		<?php  if ( have_posts() ) :  while ( have_posts() ) : the_post();  ?>
				<?php get_template_part('content',''); ?>
				<?php endwhile; ?>
				<?php else : ?>
					
				<h2><?php esc_html_e('Nothing Found', 'quality'); ?></h2>
				<p><?php esc_html_e('Sorry, but nothing matched your search criteria. Please try again with some different keywords.','quality'); ?></p>
				<div class="paginations">
					<!-- Pagination -->			
					<?php
					// Previous/next page navigation.
						the_posts_pagination( array(
					'prev_text'          => '<i class="fa fa-angle-double-left"></i>',
					'next_text'          => '<i class="fa fa-angle-double-right"></i>',
					) );
					?>
				</div>
				
				<?php endif; ?>
		</div>	
		<!--/Blog Content-->
		
		<?php get_sidebar(); ?>	
		
		</div>
	</div>
</section>
<?php get_footer();