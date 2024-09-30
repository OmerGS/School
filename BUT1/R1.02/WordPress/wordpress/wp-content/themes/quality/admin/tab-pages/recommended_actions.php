<?php
$quality_actions = $this->recommended_actions;
$quality_actions_todo = get_option('quality_recommended_actions', false);
?>
<div id="recommended_actions" class="quality-tab-pane panel-close">
    <div class="action-list row">
        <?php if ($quality_actions): foreach ($quality_actions as $key => $quality_actionValue): ?>
                <div class="action col-md-6 col-sm-6 col-xs-12" id="<?php echo esc_attr($quality_actionValue['id']); ?>">
                    <div class="recommended_box">
                        <img width="772" height="180" src="<?php echo esc_url(QUALITY_TEMPLATE_DIR_URI) . '/images/' . str_replace(' ', '-', strtolower($quality_actionValue['title'])) . '.png'; ?>">
                        <div class="action-inner">
                            <h3 class="action-title"><?php echo esc_html($quality_actionValue['title']); ?></h3>
                            <div class="action-desc"><?php echo esc_html($quality_actionValue['desc']); ?></div>
                            <?php echo $quality_actionValue['link']; ?>
                            <div class="action-watch">
                                <?php if (!$quality_actionValue['is_done']): ?>
                                    <?php if (!isset($quality_actions_todo[$quality_actionValue['id']]) || !$quality_actions_todo[$quality_actionValue['id']]): ?>
                                        <span class="dashicons dashicons-visibility"></span>
                                    <?php else: ?>
                                        <span class="dashicons dashicons-hidden"></span>
                                    <?php endif; ?>
                                <?php else: ?>
                                    <span class="dashicons dashicons-yes"></span>
                                <?php endif; ?>
                            </div>
                        </div>
                    </div>
                </div>
            <?php endforeach;
        endif; ?>
    </div>
</div>