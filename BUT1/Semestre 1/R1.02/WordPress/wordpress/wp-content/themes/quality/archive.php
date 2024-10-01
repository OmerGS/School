<?php 
get_header();
quality_breadcrumbs(); ?>
<section id="section-block" class="site-content">
	<div class="container">
		<div class="row">
		<!--Blog Posts-->
		<?php echo '<div class="col-md-'.( !is_active_sidebar( "sidebar-primary" ) ?"12" :"8" ).' col-xs-12">'; ?>
			<div class="news">
				<?php if ( have_posts() ) :
					// Start the Loop.
					while ( have_posts() ) : the_post();
					get_template_part('content','');
					endwhile;
					
					?>
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
					<?php endif;
					?>				
			</div>	
		<!--/Blog Content-->	
		</div>
		<?php get_sidebar(); ?>
	</div>
<!--</div>-->
</section>
<?php 
get_footer();